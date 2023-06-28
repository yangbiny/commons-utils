package com.reason.commons.utils.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author impassive
 */
public class CsvFileUtils {

  public static void writeCsvToExcel(String csvPath, String excelPath) throws IOException {
    writeCsvToExcel(new File(csvPath), new File(excelPath), ",");
  }

  public static void writeCsvToExcel(String csvPath, String excelPath, String csvSeparator)
      throws IOException {
    writeCsvToExcel(new File(csvPath), new File(excelPath), csvSeparator);
  }

  /**
   * 将csv文件写入到excel文件中。 默认认为 CSV 的 第一行 是 表头，所以会将第一行的数据写入到 Excel 的第一行中。
   *
   * @param csvFile 需要转换的 CSV文件
   * @param excelFile 需要写入的目的Excel文件
   * @param csvSeparator CSV 数据的分割器
   * @throws IOException IO异常
   */
  public static void writeCsvToExcel(File csvFile, File excelFile, String csvSeparator)
      throws IOException {
    try (LineIterator iterator = FileUtils.lineIterator(csvFile);
        var workbook = new XSSFWorkbook();
        var fileOut = new FileOutputStream(excelFile)
    ) {
      if (!iterator.hasNext()) {
        throw new NoSuchElementException("csv 文件不能为空");
      }
      String[] headers = iterator.nextLine().split(csvSeparator);

      XSSFSheet sheet = workbook.createSheet("sheet1");
      var rowIndex = 0;
      var columnIndex = 0;
      var row = sheet.createRow(rowIndex++);

      for (String header : headers) {
        XSSFCell cell = row.createCell(columnIndex++);
        cell.setCellValue(header);
      }
      while (iterator.hasNext()) {
        String[] data = iterator.nextLine().split(csvSeparator);
        row = sheet.createRow(rowIndex++);
        columnIndex = 0;
        for (String datum : data) {
          XSSFCell cell = row.createCell(columnIndex++);
          cell.setCellValue(datum);
        }
      }
      // 保存Excel文件
      workbook.write(fileOut);
    }
  }


}

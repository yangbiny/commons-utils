package com.reason.commons.utils.file;

import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * @author impassive
 */
class CsvFileUtilsTest {

  @Test
  void writeCsvToExcel() throws IOException {
    CsvFileUtils.writeCsvToExcel("/Users/reasonknow/Desktop/all_with_name.csv",
        "/Users/reasonknow/Desktop/all_with_name.xlsx", ",&");
  }

  @Test
  void testWriteCsvToExcel() throws IOException {
    CsvFileUtils.writeCsvToExcel("/Users/reasonknow/Desktop/all_with_name.csv",
        "/Users/reasonknow/Desktop/all_with_name.xlsx");
  }
}
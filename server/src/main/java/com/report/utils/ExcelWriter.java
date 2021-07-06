package com.report.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface ExcelWriter {

    Row createRow(final XSSFSheet sheet);

    ExcelWriter writeCell(final Integer value, final Row row, final Integer index);

    ExcelWriter writeCell(final String value, final Row row, final Integer index);

    ByteArrayOutputStream saveWorkbook(final XSSFWorkbook workbook) throws IOException;
}

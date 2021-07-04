package com.unit.report.util;

import com.common.exception.ExcelException;
import com.common.model.ErrorMessage;
import com.report.utils.ExcelWriter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;

import static com.helpers.ReportDataGenerator.DEFAULT_SHEET_NAME;
import static com.helpers.ReportDataGenerator.createWorkbookWithOneSheet;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExcelWriterTest {

    @Autowired
    private ExcelWriter excelWriter;

    @Test
    @DisplayName("Write cell - Integer - Should write value into cell")
    void writeCell_Integer_ShouldWriteValueIntoCell() {
        XSSFWorkbook workbook = createWorkbookWithOneSheet(DEFAULT_SHEET_NAME);
        Row row = workbook.getSheet(DEFAULT_SHEET_NAME).getRow(0);

        excelWriter.writeCell(10, row, 0);

        assertEquals(10, row.getCell(0).getNumericCellValue());
    }

    @Test
    @DisplayName("Write cell - Integer - Should throw exception - Value is null")
    void writeCell_Integer_ShouldThrowException_ValueIsNull() {
        XSSFWorkbook workbook = createWorkbookWithOneSheet(DEFAULT_SHEET_NAME);
        Row row = workbook.getSheet(DEFAULT_SHEET_NAME).getRow(0);
        ExcelException exception = assertThrows(ExcelException.class, () -> excelWriter.writeCell((Integer) null, row, 0));

        assertEquals(ErrorMessage.C02.getValue(), exception.getMessage());
    }

    @Test
    @DisplayName("Write cell - Integer - Should throw exception - Index is null")
    void writeCell_Integer_ShouldThrowException_IndexIsNull() {
        XSSFWorkbook workbook = createWorkbookWithOneSheet(DEFAULT_SHEET_NAME);
        Row row = workbook.getSheet(DEFAULT_SHEET_NAME).getRow(0);
        ExcelException exception = assertThrows(ExcelException.class, () -> excelWriter.writeCell(10, row, null));

        assertEquals(ErrorMessage.C02.getValue(), exception.getMessage());
    }

    @Test
    @DisplayName("Write cell - Integer - Should throw exception - Row is null")
    void writeCell_Integer_ShouldThrowException_RowIsNull() {
        ExcelException exception = assertThrows(ExcelException.class, () -> excelWriter.writeCell(0, null, 0));

        assertEquals(ErrorMessage.C02.getValue(), exception.getMessage());
    }

    @Test
    @DisplayName("Write cell - String - Should write value into cell")
    void writeCell_String_ShouldWriteValueIntoCell() {
        XSSFWorkbook workbook = createWorkbookWithOneSheet(DEFAULT_SHEET_NAME);
        Row row = workbook.getSheet(DEFAULT_SHEET_NAME).getRow(0);

        excelWriter.writeCell("TEST", row, 0);

        assertEquals("TEST", row.getCell(0).getStringCellValue());
    }


    @Test
    @DisplayName("Write cell - String - Should throw exception - Value is null")
    void writeCell_String_ShouldThrowException_ValueIsNull() {
        XSSFWorkbook workbook = createWorkbookWithOneSheet(DEFAULT_SHEET_NAME);
        Row row = workbook.getSheet(DEFAULT_SHEET_NAME).getRow(0);
        ExcelException exception = assertThrows(ExcelException.class, () -> excelWriter.writeCell((Integer) null, row, 0));

        assertEquals(ErrorMessage.C02.getValue(), exception.getMessage());
    }

    @Test
    @DisplayName("Write cell - String - Should throw exception - Index is null")
    void writeCell_String_ShouldThrowException_IndexIsNull() {
        XSSFWorkbook workbook = createWorkbookWithOneSheet(DEFAULT_SHEET_NAME);
        Row row = workbook.getSheet(DEFAULT_SHEET_NAME).getRow(0);
        ExcelException exception = assertThrows(ExcelException.class, () -> excelWriter.writeCell(10, row, null));

        assertEquals(ErrorMessage.C02.getValue(), exception.getMessage());
    }

    @Test
    @DisplayName("Write cell - String - Should throw exception - Row is null")
    void writeCell_String_ShouldThrowException_RowIsNull() {
        ExcelException exception = assertThrows(ExcelException.class, () -> excelWriter.writeCell(0, null, 0));

        assertEquals(ErrorMessage.C02.getValue(), exception.getMessage());
    }

    @Test
    @DisplayName("Save workbook - Should return ByteArrayOutputStream")
    void saveWorkbook_ShouldReturnByteArrayOutputStream() {
        XSSFWorkbook workbook = createWorkbookWithOneSheet(DEFAULT_SHEET_NAME);

        ByteArrayOutputStream result = excelWriter.saveWorkbook(workbook);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Create Row - Should create next row")
    void createRow_ShouldCreateNextRow() {
        XSSFWorkbook workbook = createWorkbookWithOneSheet(DEFAULT_SHEET_NAME);
        XSSFSheet xssfSheet = workbook.getSheet(DEFAULT_SHEET_NAME);

        Row result = excelWriter.createRow(xssfSheet);

        // Count from 0
        assertEquals(1, result.getRowNum());
        assertEquals(1, xssfSheet.getLastRowNum());
    }

    @Test
    @DisplayName("Create Row - Should throw exception - Row is null")
    void createRow_ShouldThrowException_RowIsNull() {
        ExcelException exception = assertThrows(ExcelException.class, () -> excelWriter.createRow(null));

        assertEquals(ErrorMessage.C02.getValue(), exception.getMessage());
    }
}

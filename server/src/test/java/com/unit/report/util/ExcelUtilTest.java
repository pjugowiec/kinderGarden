package com.unit.report.util;

import com.google.common.collect.ImmutableMap;
import com.report.domain.model.excel.ReportConfiguration;
import com.report.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Map;

import static com.common.util.CommonValue.PATH_TO_EXCEL_TEMPLATES;
import static com.helpers.ReportDataGenerator.DEFAULT_SHEET_NAME;
import static com.helpers.ReportDataGenerator.createWorkbookWithOneSheet;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExcelUtilTest {


    @Autowired
    private ExcelUtil excelUtil;

    @Autowired
    private ReportConfiguration reportConfiguration;

    @Value(PATH_TO_EXCEL_TEMPLATES + "TEST.xlsx")
    private Resource notExistsResource;

    @Test
    @DisplayName("Get workbook - Should return workbook")
    void getWorkbook_ShouldReturnWorkbook() throws IOException {
        XSSFWorkbook workbook = excelUtil.getWorkbook(reportConfiguration.getResource());

        assertNotNull(workbook);
        assertEquals(reportConfiguration.getDefaultSheetName(), workbook.getSheetName(0));
    }

    @Test
    @DisplayName("Get workbook - Should throw IOException - File not exists")
    void getWorkbook_ShouldThrowException_FileNotExists() {
        assertThrows(IOException.class, () -> excelUtil.getWorkbook(notExistsResource));
    }

    @Test
    @DisplayName("Replace text in sheet - Should replace text")
    void replaceTextInSheet_ShouldReplaceText() {
        XSSFWorkbook workbook = createWorkbookWithOneSheet(DEFAULT_SHEET_NAME);
        XSSFSheet sheet = workbook.getSheet(DEFAULT_SHEET_NAME);
        Row row = sheet.getRow(0);
        row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue("#TEST");

        Map<String, String> replaceMap = ImmutableMap.of("#TEST", "CHANGED");

        excelUtil.replaceTextInSheet(sheet, replaceMap);

        assertEquals("CHANGED", row.getCell(0).getStringCellValue());
    }
}

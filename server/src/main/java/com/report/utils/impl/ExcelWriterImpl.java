package com.report.utils.impl;

import com.common.exception.ExcelException;
import com.common.model.ErrorMessage;
import com.report.utils.ExcelWriter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ExcelWriterImpl implements ExcelWriter {

    @Override
    public Row createRow(final XSSFSheet sheet) {
        return sheet.createRow(incrementByOne(sheet.getLastRowNum()));
    }

    @Override
    public ExcelWriter writeCell(final Integer value, final Row row, final Integer index) {
        validateArgumentsWriteCell(value, row, index);
        row.getCell(index, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(value);
        return this;
    }

    @Override
    public ExcelWriter writeCell(String value, Row row, Integer index) {
        validateArgumentsWriteCell(value, row, index);
        row.getCell(index, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(value);
        return this;
    }

    @Override
    public ByteArrayOutputStream saveWorkbook(final XSSFWorkbook workbook) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            workbook.write(bos);
            return bos;
        } catch (IOException e) {
            throw new ExcelException(ErrorMessage.EXL03);
        }
    }


    private void validateArgumentsWriteCell(final Object value, final Row row, final Integer index) {
        if (value == null || row == null || index == null) throw new ExcelException(ErrorMessage.C02);
    }

    private Integer incrementByOne(final Integer value) {
        return value + 1;
    }
}

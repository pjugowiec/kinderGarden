package com.report.service.impl;

import com.common.exception.ExcelException;
import com.common.model.ErrorMessage;
import com.report.domain.model.excel.ReportConfiguration;
import com.report.service.GenerateFileService;
import com.report.utils.ExcelUtil;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@AllArgsConstructor
public class GenerateFileServiceImpl implements GenerateFileService {

    private final ExcelUtil excelUtil;
    private final ReportConfiguration reportConfiguration;

    @Override
    public ByteArrayOutputStream generateExcel(final Long employeeId) {
        try {
            XSSFWorkbook reportWorkBook = excelUtil.getWorkbook(reportConfiguration.getResource());


            return null;
        } catch (IOException ex) {
            throw new ExcelException(ErrorMessage.EXL01);
        }
    }
}


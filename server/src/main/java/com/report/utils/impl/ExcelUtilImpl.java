package com.report.utils.impl;

import com.report.utils.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ExcelUtilImpl implements ExcelUtil {

    @Override
    public XSSFWorkbook getWorkbook(final Resource resource) throws IOException {
        return new XSSFWorkbook(resource.getInputStream());

    }
}

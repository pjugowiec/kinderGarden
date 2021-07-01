package com.report.utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Map;

public interface ExcelUtil {

    XSSFWorkbook getWorkbook(final Resource resource) throws IOException;

    void replaceTextInSheet(final XSSFSheet sheet, final Map<String, String> values);
}

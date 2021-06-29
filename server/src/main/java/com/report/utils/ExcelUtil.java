package com.report.utils;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;

import java.io.IOException;

public interface ExcelUtil {

    XSSFWorkbook getWorkbook(final Resource resource) throws IOException;
}

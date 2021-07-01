package com.report.utils.impl;

import com.report.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class ExcelUtilImpl implements ExcelUtil {

    @Override
    public XSSFWorkbook getWorkbook(final Resource resource) throws IOException {
        return new XSSFWorkbook(resource.getInputStream());

    }

    @Override
    public void replaceTextInSheet(final XSSFSheet sheet, final Map<String, String> values) {
        sheet.rowIterator().forEachRemaining(row -> iterateByMapReplaceCellValue(row, values));
    }

    private void iterateByMapReplaceCellValue(final Row row, final Map<String, String> values) {
        values.forEach((key, value) -> row.cellIterator().forEachRemaining(cell -> {
            if (cell.getCellType().equals(CellType.STRING) && cell.getStringCellValue().contains(key))
                cell.setCellValue(cell.getStringCellValue().replace(key, value));
        }));
    }
}

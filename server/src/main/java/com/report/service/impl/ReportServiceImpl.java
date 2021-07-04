package com.report.service.impl;

import com.common.exception.ExcelException;
import com.common.model.ErrorMessage;
import com.google.common.collect.ImmutableMap;
import com.report.domain.entity.DateEntity;
import com.report.domain.entity.EmployeeEntity;
import com.report.domain.enums.DateType;
import com.report.domain.model.excel.ReportConfiguration;
import com.report.domain.model.excel.ReportModel;
import com.report.service.EmployeeService;
import com.report.service.ReportService;
import com.report.utils.ExcelUtil;
import com.report.utils.ExcelWriter;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final EmployeeService employeeService;
    private final ExcelUtil excelUtil;
    private final ExcelWriter excelWriter;
    private final ReportConfiguration reportConfiguration;

    @Override
    public ReportModel generateEmployeeReport(final Long employeeId) {
        final Integer currentYear = LocalDate.now().getYear();
        final EmployeeEntity employeeEntity = employeeService.getEmployeeWithDates(employeeId, currentYear);
        final Collection<DateEntity> dates = employeeEntity.getDates();
        if (dates.isEmpty()) throw new ExcelException(ErrorMessage.EXL02);

        try {
            XSSFWorkbook reportWorkBook = excelUtil.getWorkbook(reportConfiguration.getResource());
            XSSFSheet sheet = reportWorkBook.getSheet(reportConfiguration.getDefaultSheetName());
            excelUtil.replaceTextInSheet(sheet, createReplaceMap(employeeEntity, currentYear));

            fillReportByDates(dates, sheet);

            return ReportModel.builder()
                    .fileName(employeeEntity.concatNameWithLastName())
                    .file(excelWriter.saveWorkbook(reportWorkBook))
                    .build();

        } catch (IOException ex) {
            throw new ExcelException(ErrorMessage.EXL01);
        }
    }

    private void fillReportByDates(final Collection<DateEntity> dates, final XSSFSheet sheet) {

        EnumMap<DateType, Integer> globalCounter = new EnumMap<>(DateType.class);
        dates.stream()
                .collect(Collectors.groupingBy(date -> date.getDate().getMonthValue()))
                .forEach((key, value) -> {
                    final Integer rowMonthIndex = reportConfiguration.getRowMonthIndex(key);
                    final Row row = sheet.getRow(rowMonthIndex);
                    EnumMap<DateType, Integer> monthCounter = new EnumMap<>(DateType.class);
                    value.forEach(entity -> writeMonthValuesInRowUpdateMonthCounter(entity, row, monthCounter));
                    monthCounter.forEach((dateType, count) ->
                            excelWriter.writeCell(count, row, dateType.getIndex()));
                    globalCounter.putAll(monthCounter);
                });

        final Row row = sheet.getRow(reportConfiguration.getSummaryRowIndex());
        globalCounter.forEach((dateType, count) ->
                excelWriter.writeCell(count, row, dateType.getIndex()));
    }

    private EnumMap<DateType, Integer> writeMonthValuesInRowUpdateMonthCounter(DateEntity entity, Row row, EnumMap<DateType, Integer> monthCounter) {
        final Integer columnDayIndex = reportConfiguration.getColumnDayIndex(entity.getDate().getDayOfMonth());
        DateType dateType = entity.getType();
        excelWriter.writeCell(dateType.getValue(), row, columnDayIndex);
        updateCounterMap(monthCounter, dateType);
        return monthCounter;
    }

    private void updateCounterMap(EnumMap<DateType, Integer> monthCounter, DateType dateType) {
        if (monthCounter.containsKey(dateType)) {
            monthCounter.computeIfPresent(dateType, (k, v) -> ++v);
        } else {
            monthCounter.put(dateType, 1);
        }
    }

    private Map<String, String> createReplaceMap(final EmployeeEntity employee, final Integer currentYear) {
        return ImmutableMap.of(
                "#YEAR", currentYear.toString(),
                "#ETAT", employee.getRegularPost(),
                "#FULLNAME", employee.concatNameWithLastName(),
                "#WORK", employee.getPosition());
    }
}


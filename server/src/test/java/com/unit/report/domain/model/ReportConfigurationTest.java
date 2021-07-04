package com.unit.report.domain.model;

import com.common.exception.ExcelException;
import com.report.domain.model.excel.ReportConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ReportConfigurationTest {

    @Autowired
    private ReportConfiguration reportConfiguration;

    @Test
    @DisplayName("Get Row Month Index - Should return correct index")
    void getRowMonthIndex_ShouldReturnCorrectIndex() {
        Integer result = reportConfiguration.getRowMonthIndex(10);

        assertEquals(27, result);
    }

    @Test
    @DisplayName("Get Row Month Index - To big month - Should throw exception")
    void getRowMonthIndex_ToBigMonth_ShouldReturnThrowException() {
        ExcelException exception = assertThrows(ExcelException.class, () -> reportConfiguration.getRowMonthIndex(32));

        assertEquals("Value 32 is bigger than 12", exception.getMessage());
    }

    @Test
    @DisplayName("Get Column Month Index - Should return correct index")
    void getColumnDayIndex_ShouldReturnCorrectIndex() {
        Integer result = reportConfiguration.getColumnDayIndex(1);

        assertEquals(2, result);
    }

    @Test
    @DisplayName("Get Column Month Index  - To big day - Should throw exception")
    void getColumnDayIndex_ToBigDay_ShouldReturnThrowException() {
        ExcelException exception = assertThrows(ExcelException.class, () -> reportConfiguration.getColumnDayIndex(32));

        assertEquals("Value 32 is bigger than 31", exception.getMessage());
    }
}

package com.integration.report.service;

import com.common.exception.NotFoundException;
import com.common.model.ErrorMessage;
import com.report.service.ReportService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ReportServiceTest {

    @Autowired
    private ReportService reportService;

    @Test
    @DisplayName("Generate Employee Report - Should throw exception - Employee not exists")
    void generateEmployeeReport_ShouldThrowException_EmployeeNotExists() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> reportService.generateEmployeeReport(100000L));

        assertEquals(ErrorMessage.EMP01.getValue(), exception.getMessage());
    }

    @Test
    @DisplayName("Generate Employee Report - Should throw exception - Employee id is null")
    void generateEmployeeReport_ShouldThrowException_EmployeeIdIsNull() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> reportService.generateEmployeeReport(null));

        assertEquals(ErrorMessage.EMP01.getValue(), exception.getMessage());
    }
}

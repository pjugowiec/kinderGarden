package com.integration.report.service;

import com.common.exception.NotFoundException;
import com.common.model.ErrorMessage;
import com.report.domain.model.DateData;
import com.report.repository.DateRepository;
import com.report.service.DateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sql.SqlDateInit;

import java.util.List;

import static com.helpers.ReportDataGenerator.createDateData;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@SqlDateInit
class DateServiceTest {

    private final DateService dateService;
    private final DateRepository dateRepository;

    @Autowired
    DateServiceTest(DateService dateService, DateRepository dateRepository) {
        this.dateService = dateService;
        this.dateRepository = dateRepository;
    }

    @Test
    @DisplayName("Update Dates - Should replace dates")
    void updateDates_ShouldReplaceDates() {
        Long employeeId = 1000L;
        List<DateData> dateData = asList(createDateData());
        dateService.updateDatesForEmployee(dateData, employeeId);

        assertEquals(1, dateRepository.findAll().stream().filter(v -> v.getEmployee().getId()
                .equals(employeeId)).count());
    }

    @Test
    @DisplayName("Update Dates - Should throw NotFoundException, employee not exists")
    void updateDates_ShouldThrowE£xce£ption_EmployeeNotExists() {
        Long employeeId = 100000L;

        DateData dateData = createDateData();
        dateData.setEmployeeId(employeeId);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> dateService.updateDatesForEmployee(asList(dateData), employeeId));

        assertEquals(ErrorMessage.EMP01.getValue(), exception.getMessage());
    }
}

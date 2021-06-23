package com.helpers;

import com.report.domain.entity.EmployeeEntity;
import com.report.domain.enums.DateType;
import com.report.domain.model.DateData;
import com.report.domain.model.employee.EmployeeForm;

import java.time.LocalDate;

public final class ReportDataGenerator {

    public static EmployeeForm createEmployeeForm() {
        return EmployeeForm.builder()
                .id(1L)
                .name("Name")
                .lastname("LastName")
                .position("Position")
                .regularPost("RegularPost")
                .countOfChildrenCare(20)
                .countOfVacation(8)
                .build();
    }

    public static EmployeeEntity createEmployeeEntity() {
        return EmployeeEntity.builder()
                .id(2L)
                .name("EmployeeName")
                .lastname("EmployeeLastname")
                .position("EmployeePosition")
                .regularPost("EmployeeRegularPost")
                .countOfChildrenCare(15)
                .countOfVacation(10)
                .build();
    }

    public static DateData createDateData() {
        return DateData.builder()
                .id(1L)
                .date(LocalDate.of(2020, 5, 10))
                .type(DateType.Inne)
                .employeeId(1000L)
                .build();
    }

}

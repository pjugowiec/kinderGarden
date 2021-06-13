package com.helpers;

import com.report.domain.entity.EmployeeEntity;
import com.report.domain.model.employee.EmployeeForm;

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
}

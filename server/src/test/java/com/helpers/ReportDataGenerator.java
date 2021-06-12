package com.helpers;

import com.raport.domain.model.employee.EmployeeForm;

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
}

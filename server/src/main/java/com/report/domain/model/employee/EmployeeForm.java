package com.report.domain.model.employee;

import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
public class EmployeeForm extends Employee {

    public EmployeeForm(Long id, String name, String lastname, String regularPost,
                        Integer countOfVacation, Integer countOfChildrenCare, String position) {
        super(id, name, lastname, regularPost, countOfVacation, countOfChildrenCare, position);
    }
}

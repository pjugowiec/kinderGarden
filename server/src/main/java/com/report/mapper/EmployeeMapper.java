package com.report.mapper;

import com.report.domain.entity.EmployeeEntity;
import com.report.domain.model.employee.EmployeeForm;

public interface EmployeeMapper {

    EmployeeEntity employeeFormToEmployeeEntity(final EmployeeForm employee);

    EmployeeEntity updateEmployeeEntityByEmployeeForm(EmployeeEntity employeeEntity, final EmployeeForm employeeForm);
}

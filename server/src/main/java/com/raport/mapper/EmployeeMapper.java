package com.raport.mapper;

import com.raport.domain.entity.EmployeeEntity;
import com.raport.domain.model.employee.EmployeeForm;

public interface EmployeeMapper {

    EmployeeEntity employeeFormToEmployeeEntity(final EmployeeForm employee);
}

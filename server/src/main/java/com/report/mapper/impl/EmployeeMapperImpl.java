package com.report.mapper.impl;

import com.report.domain.entity.EmployeeEntity;
import com.report.domain.model.employee.EmployeeForm;
import com.report.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeEntity employeeFormToEmployeeEntity(final EmployeeForm employee) {
        return EmployeeEntity.builder()
                .name(employee.getName())
                .lastname(employee.getLastname())
                .position(employee.getPosition())
                .regularPost(employee.getRegularPost())
                .countOfChildrenCare(employee.getCountOfChildrenCare())
                .countOfVacation(employee.getCountOfVacation())
                .build();
    }
}

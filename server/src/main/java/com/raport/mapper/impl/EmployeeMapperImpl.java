package com.raport.mapper.impl;

import com.raport.domain.entity.EmployeeEntity;
import com.raport.domain.model.employee.EmployeeForm;
import com.raport.mapper.EmployeeMapper;
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

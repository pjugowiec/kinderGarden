package com.cardIndex.services;

import com.cardIndex.domain.dto.EmployeeDto;
import com.cardIndex.domain.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getAll();
    void createEmployee(final EmployeeDto employee);
    void updateEmployee(final EmployeeDto employee, final Long id) throws Exception;
    void deleteEmployee(final Long id);
    EmployeeDto findEmployeeByID(final Long id) throws Exception;

}

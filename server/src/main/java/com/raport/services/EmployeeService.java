package com.raport.services;

import com.raport.domain.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getAll();
    void createEmployee(final EmployeeDto employee);
    void updateEmployee(final EmployeeDto employee, final Long id) throws Exception;
    void deleteEmployee(final Long id);
    EmployeeDto findEmployeeByID(final Long id) throws Exception;

}

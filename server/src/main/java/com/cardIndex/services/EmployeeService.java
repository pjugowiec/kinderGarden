package com.cardIndex.services;

import com.cardIndex.domain.dto.EmployeeDto;
import com.cardIndex.domain.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getAll();
    void createEmployee(EmployeeDto employee);
    void updateEmployee(EmployeeDto employee, long id) throws Exception;
    void deleteEmployee(long id);
    EmployeeDto findEmployeeByID(long id) throws Exception;

}

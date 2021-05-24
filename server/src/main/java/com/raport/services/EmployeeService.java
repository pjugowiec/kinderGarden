package com.raport.services;

import com.raport.domain.model.EmployeeDto;

import java.util.Collection;

public interface EmployeeService {

    /**
     * Get employees without filers
     *
     * @return Collection of {@link EmployeeDto}
     */
    Collection<EmployeeDto> getEmployees();

    void createEmployee(final EmployeeDto employee);
    void updateEmployee(final EmployeeDto employee, final Long id) throws Exception;
    void deleteEmployee(final Long id);
    EmployeeDto findEmployeeByID(final Long id) throws Exception;

}

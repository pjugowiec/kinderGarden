package com.raport.services;

import com.raport.domain.model.EmployeeTable;

import java.util.Collection;

public interface EmployeeService {

    /**
     * Get employees without filers
     *
     * @return Collection of {@link EmployeeTable}
     */
    Collection<EmployeeTable> getEmployees();

//    void createEmployee(final EmployeeDto employee);
//    void updateEmployee(final EmployeeDto employee, final Long employeeId);
//    void deleteEmployee(final Long employeeId);
//    EmployeeDto findById(final Long employeeId);

}

package com.raport.services;

import java.util.Collection;

import com.raport.domain.model.employee.EmployeeTable;

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

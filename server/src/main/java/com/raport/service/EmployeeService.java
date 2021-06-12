package com.raport.service;

import java.util.Collection;

import com.raport.domain.model.employee.EmployeeForm;
import com.raport.domain.model.employee.EmployeeTable;

public interface EmployeeService {

    /**
     * Get employees without filers
     *
     * @return Collection of {@link EmployeeTable}
     */
    Collection<EmployeeTable> getEmployees();

    void createEmployee(final EmployeeForm employee);

//    void updateEmployee(final EmployeeDto employee, final Long employeeId);
//    void deleteEmployee(final Long employeeId);
//    EmployeeDto findById(final Long employeeId);

}

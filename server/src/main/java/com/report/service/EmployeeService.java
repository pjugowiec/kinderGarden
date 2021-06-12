package com.report.service;

import java.util.Collection;

import com.report.domain.model.employee.EmployeeForm;
import com.report.domain.model.employee.EmployeeTable;

public interface EmployeeService {

    /**
     * Get employees without filers
     *
     * @return Collection of {@link EmployeeTable}
     */
    Collection<EmployeeTable> getEmployees();

    void createEmployee(final EmployeeForm employee);

    EmployeeForm findById(final Long id);

//    void updateEmployee(final EmployeeDto employee, final Long employeeId);
//    void deleteEmployee(final Long employeeId);


}

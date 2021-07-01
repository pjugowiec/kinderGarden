package com.report.service;

import java.util.Collection;

import com.report.domain.entity.EmployeeEntity;
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

    void deleteEmployee(final Long employeeId);
    void updateEmployee(final EmployeeForm employee, final Long id);

    EmployeeEntity getEmployeeWithDates(final Long id, final Integer year);
}

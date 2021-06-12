package com.integration.report.service;

import com.raport.domain.entity.EmployeeEntity;
import com.raport.domain.model.employee.EmployeeForm;
import com.raport.domain.model.employee.EmployeeTable;
import com.raport.repository.EmployeeRepository;
import com.raport.service.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sql.SqlEmployeeInit;

import java.util.Collection;

import static com.helpers.ReportDataGenerator.createEmployeeForm;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@SqlEmployeeInit
class EmployeeServiceTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Test
    @DisplayName("Get employees - should return all employees")
    void getEmployees_ShouldReturnAllEmployees() {
        Collection<EmployeeTable> employees = employeeService.getEmployees();

        assertEquals(employeeRepository.count(), employees.size());
    }

    @Test
    @DisplayName("Create employee - should create employee")
    void createEmployee_ShouldCreateEmployee() {
        final EmployeeForm employeeForm = createEmployeeForm();

        employeeService.createEmployee(employeeForm);

        assertEquals(4L, employeeRepository.count());

        EmployeeEntity employeeEntity = employeeRepository.findAll()
                .stream()
                .filter(v -> v.getName().equals(employeeForm.getName()))
                .findFirst()
                .get();

        assertEquals(employeeForm.getName(), employeeEntity.getName());
        assertEquals(employeeForm.getLastname(), employeeEntity.getLastname());
        assertEquals(employeeForm.getPosition(), employeeEntity.getPosition());
        assertEquals(employeeForm.getRegularPost(), employeeEntity.getRegularPost());
        assertEquals(employeeForm.getCountOfVacation(), employeeEntity.getCountOfVacation());
        assertEquals(employeeForm.getCountOfChildrenCare(), employeeEntity.getCountOfChildrenCare());
    }
}

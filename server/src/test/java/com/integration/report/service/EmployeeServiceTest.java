package com.integration.report.service;

import com.common.exception.NotFoundException;
import com.common.model.ErrorMessages;
import com.report.domain.entity.EmployeeEntity;
import com.report.domain.model.employee.EmployeeForm;
import com.report.domain.model.employee.EmployeeTable;
import com.report.repository.EmployeeRepository;
import com.report.service.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sql.SqlEmployeeInit;

import java.util.Collection;

import static com.helpers.ReportDataGenerator.createEmployeeForm;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    @DisplayName("Find employee by id - should return employee")
    void findEmployeeById_ShouldReturnEmployee() {
        EmployeeForm employeeForm = employeeService.findById(1000L);

        assertEquals(1000L, employeeForm.getId());
        assertEquals("Adam", employeeForm.getName());
        assertEquals("Smith", employeeForm.getLastname());
        assertEquals("Manage", employeeForm.getPosition());
        assertEquals("Full", employeeForm.getRegularPost());
        assertEquals(26, employeeForm.getCountOfVacation());
        assertEquals(8, employeeForm.getCountOfChildrenCare());
    }

    @Test
    @DisplayName("Find employee by id - should throw NotFoundException")
    void findEmployeeById_ShouldThrowNotFoundException() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> employeeService.findById(1L));

        assertEquals(ErrorMessages.EMP01.getValue(), exception.getMessage());
    }
}

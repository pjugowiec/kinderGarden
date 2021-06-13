package com.integration.report.service;

import com.common.exception.NotFoundException;
import com.common.model.ErrorMessage;
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
import java.util.Optional;

import static com.helpers.ReportDataGenerator.createEmployeeForm;
import static org.junit.jupiter.api.Assertions.*;

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

        assertEquals(ErrorMessage.EMP01.getValue(), exception.getMessage());
    }

    @Test
    @DisplayName("Delete employee by id - should delete employee")
    void deleteEmployeeById_ShouldDeleteEmployee() {
        final long repoCount = employeeRepository.count();

        employeeService.deleteEmployee(1000L);

        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(1000L);

        assertTrue(optionalEmployeeEntity.isEmpty());
        assertEquals(repoCount - 1, employeeRepository.count());
    }

    @Test
    @DisplayName("Delete employee by id - should throw and not delete employee")
    void deleteEmployeeById_ShouldThrowAndNotDeleteEmployee() {
        final long repoCount = employeeRepository.count();

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> employeeService.deleteEmployee(1L));

        assertEquals(ErrorMessage.EMP01.getValue(), exception.getMessage());
        assertEquals(repoCount, employeeRepository.count());
    }

    @Test
    @DisplayName("Update employee - should update entity")
    void updateEmployee_ShouldUpdateEntity() {
        final EmployeeForm employeeForm = createEmployeeForm();

        employeeService.updateEmployee(employeeForm, 1000L);

        EmployeeEntity employeeEntity = employeeRepository.findById(1000L).orElseThrow(() -> new NotFoundException(ErrorMessage.EMP01));

        assertEquals(1000L, employeeEntity.getId());
        assertEquals(employeeForm.getName(), employeeEntity.getName());
        assertEquals(employeeForm.getLastname(), employeeEntity.getLastname());
        assertEquals(employeeForm.getPosition(), employeeEntity.getPosition());
        assertEquals(employeeForm.getRegularPost(), employeeEntity.getRegularPost());
        assertEquals(employeeForm.getCountOfVacation(), employeeEntity.getCountOfVacation());
        assertEquals(employeeForm.getCountOfChildrenCare(), employeeEntity.getCountOfChildrenCare());
    }

    @Test
    @DisplayName("Update employee - should throw not found exception")
    void updateEmployee_ShouldThrowNotFoundException() {
        final EmployeeForm employeeForm = createEmployeeForm();

        NotFoundException exception = assertThrows(NotFoundException.class, () -> employeeService.updateEmployee(employeeForm, 1L));

        assertEquals(ErrorMessage.EMP01.getValue(), exception.getMessage());
    }
}

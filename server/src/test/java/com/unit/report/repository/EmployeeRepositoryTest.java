package com.unit.report.repository;

import com.report.domain.model.employee.EmployeeForm;
import com.report.domain.model.employee.EmployeeTable;
import com.report.repository.EmployeeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sql.SqlDateInit;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@SqlDateInit
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("Find all employees - should return all employees")
    void findAllEmployees_ShouldReturnAllEmployees() {
        List<EmployeeTable> employees = employeeRepository.findAllEmployees();

        assertEquals(employeeRepository.count(), employees.size());
    }

    @Test
    @DisplayName("Find employee by id - should return employee")
    void findEmployeeById_ShouldReturnEmployee() {
        Optional<EmployeeForm> optionalEmployeeForm = employeeRepository.findEmployeeById(1000L);

        assertTrue(optionalEmployeeForm.isPresent());
        EmployeeForm employeeForm = optionalEmployeeForm.get();
        assertEquals(1000L, employeeForm.getId());
        assertEquals("Adam", employeeForm.getName());
        assertEquals("Smith", employeeForm.getLastname());
        assertEquals("Manage", employeeForm.getPosition());
        assertEquals("Full", employeeForm.getRegularPost());
        assertEquals(26, employeeForm.getCountOfVacation());
        assertEquals(8, employeeForm.getCountOfChildrenCare());
    }

    @Test
    @DisplayName("Find employee by id - should return nothing")
    void findEmployeeById_ShouldReturnNothing() {
        Optional<EmployeeForm> optionalEmployeeForm = employeeRepository.findEmployeeById(1L);

        assertFalse(optionalEmployeeForm.isPresent());
    }
}

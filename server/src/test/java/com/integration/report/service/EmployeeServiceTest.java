package com.integration.report.service;

import com.raport.domain.model.employee.EmployeeTable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.raport.repository.EmployeeRepository;
import com.raport.services.EmployeeService;

import sql.SqlEmployeeInit;

import java.util.Collection;

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
}

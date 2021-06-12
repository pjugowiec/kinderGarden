package com.unit.report.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.raport.domain.model.employee.EmployeeTable;
import com.raport.repository.EmployeeRepository;

import sql.SqlEmployeeInit;

@SpringBootTest
@SqlEmployeeInit
class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	@DisplayName("Get employees - should return all employees")
	void getEmployees_shouldReturnAllEmployees() {
		List<EmployeeTable> employees = employeeRepository.findAllEmployees();

		assertEquals(employeeRepository.count(), employees.size());
	}
}

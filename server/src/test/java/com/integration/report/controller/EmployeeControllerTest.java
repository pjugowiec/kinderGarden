package com.integration.report.controller;


import com.common.model.ErrorMessages;
import com.common.model.ErrorResponse;
import com.config.template.ControllerTemplate;
import com.report.domain.entity.EmployeeEntity;
import com.report.domain.model.employee.EmployeeForm;
import com.report.repository.EmployeeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import sql.SqlEmployeeInit;

import java.util.Optional;

import static com.common.model.ResourceUrl.EMPLOYEE;
import static com.helpers.CommonValues.*;
import static com.helpers.ReportDataGenerator.createEmployeeForm;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

@SqlEmployeeInit
class EmployeeControllerTest extends ControllerTemplate {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    @DisplayName("Get employees - should return all employees")
    void getEmployees_GET_ShouldReturnAllEmployees() {

        given(requestSpecification)
                .get(API_PREFIX + EMPLOYEE)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body(JSON_SIZE, is(3));
    }

    @Test
    @DisplayName("Create employee - should create employee")
    void createEmployee_POST_ShouldCreateEmployee() {
        final EmployeeForm employeeForm = createEmployeeForm();

        given(requestSpecification)
                .body(employeeForm)
                .post(API_PREFIX + EMPLOYEE)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());

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
        EmployeeForm response = given(requestSpecification)
                .get(API_PREFIX + EMPLOYEE + ID_PATH, 1000L)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(EmployeeForm.class);

        assertEquals(1000L, response.getId());
        assertEquals("Adam", response.getName());
        assertEquals("Smith", response.getLastname());
        assertEquals("Manage", response.getPosition());
        assertEquals("Full", response.getRegularPost());
        assertEquals(26, response.getCountOfVacation());
        assertEquals(8, response.getCountOfChildrenCare());
    }

    @Test
    @DisplayName("Find employee by id - should throw exception")
    void findEmployeeById_ShouldThrowException() {
        ErrorResponse response = given(requestSpecification)
                .get(API_PREFIX + EMPLOYEE + ID_PATH, 1L)
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .extract()
                .as(ErrorResponse.class);

        assertEquals(ErrorMessages.EMP01.toString(), response.getErrorCode());
        assertEquals(ErrorMessages.EMP01.getValue(), response.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, response.getHttpStatus());
        assertNotNull(response.getTime());
    }

    @Test
    @DisplayName("Delete employee - should delete employee")
    void deleteEmployee_ShouldDeleteEmployee() {
        final long repoCount = employeeRepository.count();

        given(requestSpecification)
                .delete(API_PREFIX + EMPLOYEE + ID_PATH, 1000L)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());

        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(1000L);

        assertTrue(optionalEmployeeEntity.isEmpty());
        assertEquals(repoCount - 1, employeeRepository.count());
    }

    @Test
    @DisplayName("Delete employee - should throw exception and not delete")
    void deleteEmployee_ShouldThrowExceptionAndNotDelete() {
        final long repoCount = employeeRepository.count();

        ErrorResponse response = given(requestSpecification)
                .delete(API_PREFIX + EMPLOYEE + ID_PATH, 1L)
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .extract()
                .as(ErrorResponse.class);

        assertEquals(repoCount, employeeRepository.count());
        assertEquals(ErrorMessages.EMP01.toString(), response.getErrorCode());
        assertEquals(ErrorMessages.EMP01.getValue(), response.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, response.getHttpStatus());
        assertNotNull(response.getTime());
    }

    @Test
    @DisplayName("Update employee - should update entity")
    void updateEmployee_ShouldUpdateEntity() {
        final EmployeeForm employeeForm = createEmployeeForm();

        given(requestSpecification)
                .body(employeeForm)
                .put(API_PREFIX + EMPLOYEE + ID_PATH, 1000L)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());

        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(1000L);

        assertTrue(optionalEmployeeEntity.isPresent());
        EmployeeEntity employeeEntity = optionalEmployeeEntity.get();

        assertEquals(1000L, employeeEntity.getId());
        assertEquals(employeeForm.getName(), employeeEntity.getName());
        assertEquals(employeeForm.getLastname(), employeeEntity.getLastname());
        assertEquals(employeeForm.getPosition(), employeeEntity.getPosition());
        assertEquals(employeeForm.getRegularPost(), employeeEntity.getRegularPost());
        assertEquals(employeeForm.getCountOfVacation(), employeeEntity.getCountOfVacation());
        assertEquals(employeeForm.getCountOfChildrenCare(), employeeEntity.getCountOfChildrenCare());
    }

    @Test
    @DisplayName("Delete employee - should throw exception and not delete")
    void updateEmployee_ShouldThrowExceptionAndNotDelete() {
        final EmployeeForm employeeForm = createEmployeeForm();

        ErrorResponse response = given(requestSpecification)
                .body(employeeForm)
                .put(API_PREFIX + EMPLOYEE + ID_PATH, 1L)
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .extract()
                .as(ErrorResponse.class);

        assertEquals(ErrorMessages.EMP01.toString(), response.getErrorCode());
        assertEquals(ErrorMessages.EMP01.getValue(), response.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, response.getHttpStatus());
        assertNotNull(response.getTime());
    }
}

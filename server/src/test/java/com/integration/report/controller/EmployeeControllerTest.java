package com.integration.report.controller;


import com.config.template.ControllerTemplate;
import com.raport.domain.entity.EmployeeEntity;
import com.raport.domain.model.employee.EmployeeForm;
import com.raport.repository.EmployeeRepository;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sql.SqlEmployeeInit;

import static com.common.model.ResourceUrl.EMPLOYEE;
import static com.helpers.CommonValues.API_PREFIX;
import static com.helpers.CommonValues.JSON_SIZE;
import static com.helpers.ReportDataGenerator.createEmployeeForm;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SqlEmployeeInit
class EmployeeControllerTest extends ControllerTemplate {

    @Autowired
    private EmployeeRepository employeeRepository;
∑
    @Test
    @DisplayName("Get employees - should return all employees")
    void getEmployees_GET_ShouldReturnAllEmployees() {

        given(requestSpecification)
                .get(API_PREFIX + EMPLOYEE)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
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
                .statusCode(HttpStatus.SC_OK);

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

package com.integration.report.controller;


import com.config.template.ControllerTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sql.SqlEmployeeInit;

import static com.common.model.ResourceUrl.EMPLOYEE;
import static com.helpers.CommonValues.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@SqlEmployeeInit
class EmployeeControllerTest extends ControllerTemplate {

    @Test
    @DisplayName("Get employees - should return all employees")
    void getEmployees_GET_ShouldReturnAllEmployees() {

        given(requestSpecification)
                .get(API_PREFIX + EMPLOYEE)
                .then()
                .assertThat()
                .statusCode(200)
                .body(JSON_SIZE, is(3));
    }
}

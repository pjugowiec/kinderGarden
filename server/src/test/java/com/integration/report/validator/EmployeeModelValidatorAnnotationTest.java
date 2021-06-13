package com.integration.report.validator;

import com.common.model.ErrorMessage;
import com.common.model.ErrorResponse;
import com.config.template.ControllerTemplate;
import com.report.domain.model.employee.EmployeeForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.common.model.ResourceUrl.EMPLOYEE;
import static com.helpers.CommonValues.API_PREFIX;
import static com.helpers.ReportDataGenerator.createEmployeeForm;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeModelValidatorAnnotationTest extends ControllerTemplate {


    @Test
    @DisplayName("Validate null annotation for name property - should throw exception")
    void employeeModelValidator_Name_ShouldThrowException() {
        EmployeeForm employeeForm = createEmployeeForm();
        employeeForm.setName(null);

        ErrorResponse response = given(requestSpecification)
                .body(employeeForm)
                .post(API_PREFIX + EMPLOYEE)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .as(ErrorResponse.class);

        assertEquals(ErrorMessage.NULL_FIELD, response.getMessage());
    }
}

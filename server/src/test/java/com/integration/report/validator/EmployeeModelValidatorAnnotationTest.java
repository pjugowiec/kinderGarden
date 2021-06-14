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
    @DisplayName("Validate null annotation when name is null - should throw exception")
    void employeeModelValidator_NameIsNull_ShouldThrowException() {
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

        assertEquals(ErrorMessage.AnnotationMessage.NULL_FIELD, response.getMessage());
        assertEquals(ErrorMessage.V00.name(), response.getErrorCode());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    @DisplayName("Validate null annotation when name is less then three characters - should throw exception")
    void employeeModelValidator_NameIsLessThenThreeChar_ShouldThrowException() {
        EmployeeForm employeeForm = createEmployeeForm();
        employeeForm.setName("22");

        ErrorResponse response = given(requestSpecification)
                .body(employeeForm)
                .post(API_PREFIX + EMPLOYEE)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .as(ErrorResponse.class);

        assertEquals(ErrorMessage.AnnotationMessage.THREE_LENGTH, response.getMessage());
        assertEquals(ErrorMessage.V02.name(), response.getErrorCode());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    @DisplayName("Validate null annotation when lastName is null - should throw exception")
    void employeeModelValidator_LastNameIsNull_ShouldThrowException() {
        EmployeeForm employeeForm = createEmployeeForm();
        employeeForm.setLastname(null);

        ErrorResponse response = given(requestSpecification)
                .body(employeeForm)
                .post(API_PREFIX + EMPLOYEE)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .as(ErrorResponse.class);

        assertEquals(ErrorMessage.AnnotationMessage.NULL_FIELD, response.getMessage());
        assertEquals(ErrorMessage.V00.name(), response.getErrorCode());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    @DisplayName("Validate null annotation when lastname is less then three characters - should throw exception")
    void employeeModelValidator_LastnameIsLessThenThreeChar_ShouldThrowException() {
        EmployeeForm employeeForm = createEmployeeForm();
        employeeForm.setLastname("22");

        ErrorResponse response = given(requestSpecification)
                .body(employeeForm)
                .post(API_PREFIX + EMPLOYEE)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .as(ErrorResponse.class);

        assertEquals(ErrorMessage.AnnotationMessage.THREE_LENGTH, response.getMessage());
        assertEquals(ErrorMessage.V02.name(), response.getErrorCode());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    @DisplayName("Validate null annotation when regularPost is null - should throw exception")
    void employeeModelValidator_RegularPostIsNull_ShouldThrowException() {
        EmployeeForm employeeForm = createEmployeeForm();
        employeeForm.setRegularPost(null);

        ErrorResponse response = given(requestSpecification)
                .body(employeeForm)
                .post(API_PREFIX + EMPLOYEE)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .as(ErrorResponse.class);

        assertEquals(ErrorMessage.AnnotationMessage.NULL_FIELD, response.getMessage());
        assertEquals(ErrorMessage.V00.name(), response.getErrorCode());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    @DisplayName("Validate null annotation when regularPost is less then three characters - should throw exception")
    void employeeModelValidator_RegularPostIsLessThenThreeChar_ShouldThrowException() {
        EmployeeForm employeeForm = createEmployeeForm();
        employeeForm.setRegularPost("22");

        ErrorResponse response = given(requestSpecification)
                .body(employeeForm)
                .post(API_PREFIX + EMPLOYEE)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .as(ErrorResponse.class);

        assertEquals(ErrorMessage.AnnotationMessage.THREE_LENGTH, response.getMessage());
        assertEquals(ErrorMessage.V02.name(), response.getErrorCode());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    @DisplayName("Validate null annotation when countOfVacation is null - should throw exception")
    void employeeModelValidator_CountOfVacationIsNull_ShouldThrowException() {
        EmployeeForm employeeForm = createEmployeeForm();
        employeeForm.setCountOfVacation(null);

        ErrorResponse response = given(requestSpecification)
                .body(employeeForm)
                .post(API_PREFIX + EMPLOYEE)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .as(ErrorResponse.class);

        assertEquals(ErrorMessage.AnnotationMessage.NULL_FIELD, response.getMessage());
        assertEquals(ErrorMessage.V00.name(), response.getErrorCode());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    @DisplayName("Validate null annotation when countOfVacation is -2 - should throw exception")
    void employeeModelValidator_CountOfVacationIsMinus2_ShouldThrowException() {
        EmployeeForm employeeForm = createEmployeeForm();
        employeeForm.setCountOfVacation(-2);

        ErrorResponse response = given(requestSpecification)
                .body(employeeForm)
                .post(API_PREFIX + EMPLOYEE)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .as(ErrorResponse.class);

        assertEquals(ErrorMessage.AnnotationMessage.MINIMUM_ZERO, response.getMessage());
        assertEquals(ErrorMessage.V01.name(), response.getErrorCode());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    @DisplayName("Validate null annotation when countOfChildrenCare is null - should throw exception")
    void employeeModelValidator_CountOfChildrenCareIsNull_ShouldThrowException() {
        EmployeeForm employeeForm = createEmployeeForm();
        employeeForm.setCountOfChildrenCare(null);

        ErrorResponse response = given(requestSpecification)
                .body(employeeForm)
                .post(API_PREFIX + EMPLOYEE)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .as(ErrorResponse.class);

        assertEquals(ErrorMessage.AnnotationMessage.NULL_FIELD, response.getMessage());
        assertEquals(ErrorMessage.V00.name(), response.getErrorCode());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    @DisplayName("Validate null annotation when countOfChildrenCare is -2 - should throw exception")
    void employeeModelValidator_CountOfChildrenCareIsMinus2_ShouldThrowException() {
        EmployeeForm employeeForm = createEmployeeForm();
        employeeForm.setCountOfChildrenCare(-2);

        ErrorResponse response = given(requestSpecification)
                .body(employeeForm)
                .post(API_PREFIX + EMPLOYEE)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .as(ErrorResponse.class);

        assertEquals(ErrorMessage.AnnotationMessage.MINIMUM_ZERO, response.getMessage());
        assertEquals(ErrorMessage.V01.name(), response.getErrorCode());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    @DisplayName("Validate null annotation when position is null - should throw exception")
    void employeeModelValidator_PositionIsNull_ShouldThrowException() {
        EmployeeForm employeeForm = createEmployeeForm();
        employeeForm.setPosition(null);

        ErrorResponse response = given(requestSpecification)
                .body(employeeForm)
                .post(API_PREFIX + EMPLOYEE)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .as(ErrorResponse.class);

        assertEquals(ErrorMessage.AnnotationMessage.NULL_FIELD, response.getMessage());
        assertEquals(ErrorMessage.V00.name(), response.getErrorCode());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }
}

package com.integration.report.controller;

import com.config.template.ControllerTemplate;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import sql.SqlDateInit;

import static com.common.model.ResourceUrl.REPORT;
import static com.helpers.CommonValues.API_PREFIX;
import static com.helpers.CommonValues.ID_PATH;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@SqlDateInit
class ReportControllerTest extends ControllerTemplate {

    private static final String ATTACHMENT_WITH_FILE_NAME = "attachment; filename=\"%s\"";

    @Test
    @DisplayName("Get report by employee - Should generate report")
    void getReportByEmployee_GET_ShouldGenerateReport() {

        Response response = given(requestSpecification)
                .get(API_PREFIX + REPORT + ID_PATH, 1000L)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .response();

        assertNotNull(response.getHeader(HttpHeaders.CONTENT_DISPOSITION));
        assertEquals(String.format(ATTACHMENT_WITH_FILE_NAME, "Adam Smith.xlsx"), response.getHeader(HttpHeaders.CONTENT_DISPOSITION));
        assertEquals(CONTENT_DISPOSITION, response.getHeader(ACCESS_CONTROL_EXPOSE_HEADERS));
        assertEquals("application/force-download", response.getContentType());
    }

    @Test
    @DisplayName("Get report by employee - Should throw exception - Dates are empty")
    void getReportByEmployee_ShouldThrowException_DatesAreEmpty() {
        given(requestSpecification)
                .get(API_PREFIX + REPORT + ID_PATH, 1002L)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}

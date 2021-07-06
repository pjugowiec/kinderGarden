package com.integration.report.controller;

import com.common.model.ErrorMessage;
import com.common.model.ErrorResponse;
import com.config.template.ControllerTemplate;
import com.report.domain.entity.DateEntity;
import com.report.domain.model.DateData;
import com.report.repository.DateRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import sql.SqlDateInit;

import java.util.List;

import static com.common.model.ResourceUrl.DATE;
import static com.helpers.CommonValues.*;
import static com.helpers.ReportDataGenerator.createDateData;
import static io.restassured.RestAssured.given;
import static org.codehaus.groovy.runtime.InvokerHelper.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SqlDateInit
class DateControllerTest extends ControllerTemplate {

    private final DateRepository dateRepository;

    @Autowired
    public DateControllerTest(DateRepository dateRepository) {
        this.dateRepository = dateRepository;
    }

    @Test
    @DisplayName("Get dates for employee - should return dates for employee")
    void getDatesForEmployee_GET_ShouldReturnDatesForEmployee() {

        given(requestSpecification)
                .get( DATE + ID_PATH, 1000L)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body(JSON_SIZE, is(3));
    }

    @Test
    @DisplayName("Update dates for employee - should update dates")
    void updateDatesForEmployee_ShouldUpdateEntity() {
        final List<DateData> datesData = asList(createDateData());

        given(requestSpecification)
                .body(datesData)
                .put( DATE + ID_PATH, 1000L)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());

        assertEquals(1, dateRepository.findAll().stream().filter(v -> v.getEmployee().getId() == 1000).count());

        DateEntity dateEntity = dateRepository.findAll().stream().filter(v -> v.getEmployee().getId() == 1000).findAny().get();

        assertEquals(datesData.get(0).getDate(), dateEntity.getDate());
        assertEquals(datesData.get(0).getType(), dateEntity.getType());
    }

    @Test
    @DisplayName("Update dates for employee - should throw exception - employee not exists")
    void updateDatesForEmployee_ShouldThrowException_EmployeeNotExists() {
        final List<DateData> datesData = asList(createDateData());

        ErrorResponse response = given(requestSpecification)
                .body(datesData)
                .put( DATE + ID_PATH, 100000L)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .as(ErrorResponse.class);

        assertEquals(ErrorMessage.EMP01.getValue(), response.getMessage());
        assertEquals(ErrorMessage.EMP01.name(), response.getErrorCode());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }
}

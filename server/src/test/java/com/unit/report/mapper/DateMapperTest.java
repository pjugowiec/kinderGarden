package com.unit.report.mapper;

import com.common.exception.NotFoundException;
import com.common.model.ErrorMessage;
import com.report.domain.entity.DateEntity;
import com.report.domain.enums.DateType;
import com.report.domain.model.DateData;
import com.report.mapper.DateMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sql.SqlDateInit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.helpers.ReportDataGenerator.createDateData;
import static org.codehaus.groovy.runtime.InvokerHelper.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@SqlDateInit
class DateMapperTest {

    private final DateMapper dateMapper;

    @Autowired
    DateMapperTest(DateMapper dateMapper) {
        this.dateMapper = dateMapper;
    }

    @Test
    @DisplayName("Dto to entity - Should convert to entity")
    void dateDataToDateEntity_ShouldConvertToEntity() {
        final DateData dateData = createDateData();

        DateEntity dateEntity = dateMapper.dateDataToDateEntity(dateData);

        assertEquals(dateData.getDate(), dateEntity.getDate());
        assertEquals(dateData.getType(), dateEntity.getType());
        assertEquals(dateData.getEmployeeId(), dateEntity.getEmployee().getId());

    }

    @Test
    @DisplayName("Dto to entity - Should throw not found exception - employee not exists")
    void dateDataToDateEntity_ShouldThrowNotFoundException_EmployeeNotExists() {
        DateData dateData = createDateData();
        dateData.setEmployeeId(100000L);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> dateMapper.dateDataToDateEntity(dateData));

        assertEquals(ErrorMessage.EMP01.getValue(), exception.getMessage());
    }

    @Test
    @DisplayName("Collection DTO to Collection Entity - Should convert to collection of entity")
    void datesDataToDateEntities_ShouldConvertToCollectionEntity() {
        List<DateData> dtos = new ArrayList<>();
        final DateData dateDataWithId1 = createDateData();
        dtos.add(dateDataWithId1);
        final DateData dateDataWithId2 = DateData.builder()
                .id(2L)
                .type(DateType.NN)
                .employeeId(1000L)
                .date(LocalDate.of(2020, 10, 20))
                .build();
        dtos.add(dateDataWithId2);

        List<DateEntity> entities = (List<DateEntity>) dateMapper.datesDataToDateEntities(dtos);

        assertEquals(2, entities.size());
        DateEntity dateEntityWithId1 = entities.stream().filter(v -> v.getType().equals(DateType.Inne)).findAny().get();
        DateEntity dateEntityWithId2 = entities.stream().filter(v -> v.getType().equals(DateType.NN)).findAny().get();

        assertEquals(dateDataWithId1.getDate(), dateEntityWithId1.getDate());
        assertEquals(dateDataWithId1.getType(), dateEntityWithId1.getType());
        assertEquals(dateDataWithId1.getEmployeeId(), dateEntityWithId1.getEmployee().getId());

        assertEquals(dateDataWithId2.getDate(), dateEntityWithId2.getDate());
        assertEquals(dateDataWithId2.getType(), dateEntityWithId2.getType());
        assertEquals(dateDataWithId2.getEmployeeId(), dateEntityWithId2.getEmployee().getId());

    }

    @Test
    @DisplayName("Collection DTO to Collection Entity  - Should throw not found exception - employee not exists")
    void datesDataToDateEntities_ShouldThrowNotFoundException_EmployeeNotExists() {
        DateData dateData = createDateData();
        dateData.setEmployeeId(100000L);

        NotFoundException exception = assertThrows(NotFoundException.class, () ->
                dateMapper.datesDataToDateEntities(asList(dateData)));

        assertEquals(ErrorMessage.EMP01.getValue(), exception.getMessage());
    }

}
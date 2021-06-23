package com.unit.report.repository;

import com.report.domain.enums.DateType;
import com.report.domain.model.DateData;
import com.report.repository.DateRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sql.SqlDateInit;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@SqlDateInit
class DateRepositoryTest {

    private final DateRepository dateRepository;

    @Autowired
    public DateRepositoryTest(DateRepository dateRepository) {
        this.dateRepository = dateRepository;
    }

    @Test
    @DisplayName("Find dates by employee - Should return projection")
    void findDatesByEmployeeId_ShouldReturnProjection() {
        List<DateData> dtos = (List<DateData>) dateRepository.findDatesByEmployeeId(1000L);

        assertTrue(dtos.stream().findAny().get() instanceof DateData);
        assertEquals(3, dtos.size());

        DateData dateData = dtos.stream().filter(v -> v.getId() == 1000).findAny().get();

        assertEquals(LocalDate.of(2020, 5, 10), dateData.getDate());
        assertEquals(DateType.NN, dateData.getType());
    }

    @Test
    @DisplayName("Delete Date by employee id - should delete entities")
    @Transactional
    void deleteDatesByEmployeeId_ShouldDeleteEntities() {
        assertEquals(3, dateRepository.findAll().stream().filter(v -> v.getEmployee().getId() == 1000L).count());
        dateRepository.deleteDatesByEmployeeId(1000L);

        assertEquals(0, dateRepository.findAll().stream().filter(v -> v.getEmployee().getId() == 1000L).count());
    }
}

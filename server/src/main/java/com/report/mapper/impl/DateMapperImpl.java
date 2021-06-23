package com.report.mapper.impl;

import com.common.exception.NotFoundException;
import com.common.model.ErrorMessage;
import com.report.domain.entity.DateEntity;
import com.report.domain.model.DateData;
import com.report.mapper.DateMapper;
import com.report.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DateMapperImpl implements DateMapper {

    private final EmployeeRepository employeeRepository;

    @Override
    public DateEntity dateDataToDateEntity(DateData dateData) {
        return DateEntity.builder()
                .date(dateData.getDate())
                .type(dateData.getType())
                .employee(employeeRepository.findById(dateData.getEmployeeId())
                        .orElseThrow(() -> new NotFoundException(ErrorMessage.EMP01)))
                .build();
    }

    @Override
    public Collection<DateEntity> datesDataToDateEntities(Collection<DateData> dateData) {
        return dateData.stream().map(this::dateDataToDateEntity).collect(Collectors.toList());
    }
}

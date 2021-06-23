package com.report.service.impl;

import com.report.domain.entity.DateEntity;
import com.report.domain.model.DateData;
import com.report.mapper.DateMapper;
import com.report.repository.DateRepository;
import com.report.service.DateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@AllArgsConstructor
public class DatesServiceImpl implements DateService {

    private final DateRepository dateRepository;
    private final DateMapper dateMapper;

    @Override
    public Collection<DateData> getDatesForEmployee(final Long employeeId) {
        return dateRepository.findDatesByEmployeeId(employeeId);
    }

    @Override
    @Transactional
    public void updateDatesForEmployee(final Collection<DateData> dates, final Long employeeId) {
        Collection<DateEntity> entities = dateMapper.datesDataToDateEntities(dates);

        dateRepository.deleteDatesByEmployeeId(employeeId);

        dateRepository.saveAll(entities);
    }
}


package com.report.service.impl;

import com.report.domain.model.DateData;
import com.report.repository.DateRepository;
import com.report.service.DateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class DatesServiceImpl implements DateService {

    private final DateRepository dateRepository;

    @Override
    public Collection<DateData> getDatesForEmployee(final Long employeeId) {
        return dateRepository.findDatesByEmployeeId(employeeId);
    }

    @Override
    public void updateDatesForEmployee(Collection<DateData> dates) {

    }
}


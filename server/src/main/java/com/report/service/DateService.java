package com.report.service;

import com.report.domain.model.DateData;

import java.util.Collection;

public interface DateService {

    Collection<DateData> getDatesForEmployee(final Long employeeId);

    void updateDatesForEmployee(final Collection<DateData> dates, final Long employeeId);
}

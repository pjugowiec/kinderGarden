package com.report.service;

import com.report.domain.model.DatesDto;
import com.report.domain.model.ParsedDate;
import com.report.domain.entity.DatesFree;

import java.util.List;

public interface DatesService {

    List<ParsedDate> getAllDatesByEmployeeIdWithFreeDates(final Long employeeId);
    void updateDates(final List<DatesDto> datesDto, final Integer employeeId);
    void updateFreeDates(final List<DatesFree> datesFrees);
    void deleteDate(final Long dateId);
}

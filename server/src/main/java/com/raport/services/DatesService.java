package com.raport.services;

import com.raport.domain.dto.DatesDto;
import com.raport.domain.dto.ParsedDate;
import com.raport.domain.entity.DatesFree;

import java.util.List;

public interface DatesService {

    List<ParsedDate> getAllDatesByEmployeeIdWithFreeDates(final Long employeeId);
    void updateDates(final List<DatesDto> datesDto, final Integer employeeId);
    void updateFreeDates(final List<DatesFree> datesFrees);
    void deleteDate(final Long dateId);
}

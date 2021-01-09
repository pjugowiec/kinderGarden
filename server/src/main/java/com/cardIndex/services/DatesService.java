package com.cardIndex.services;

import com.cardIndex.domain.dto.DatesDto;
import com.cardIndex.domain.dto.ParsedDate;
import com.cardIndex.domain.entity.Dates;
import com.cardIndex.domain.entity.DatesFree;

import java.util.Date;
import java.util.List;

public interface DatesService {

    List<ParsedDate> getAllDatesByEmployeeIdWithFreeDates(final Long employeeId);
    void updateDates(final List<DatesDto> datesDto, final Integer employeeId);
    void updateFreeDates(final List<DatesFree> datesFrees);
    void deleteDate(final Long dateId);
}

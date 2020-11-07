package com.cardIndex.services;

import com.cardIndex.domain.dto.DatesDto;
import com.cardIndex.domain.dto.ParsedDate;
import com.cardIndex.domain.entity.Dates;
import com.cardIndex.domain.entity.DatesFree;

import java.util.Date;
import java.util.List;

public interface DatesService {

    List<ParsedDate> getAllDatesByEmployeeIdWithFreeDates(long employeeId);
    void updateDates(List<DatesDto> datesDto, Integer employeeId);
    void updateFreeDates(List<DatesFree> datesFrees);
    void deleteDate(long dateId);
}

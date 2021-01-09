package com.cardIndex.services.impl;

import com.cardIndex.domain.dto.DatesDto;
import com.cardIndex.domain.dto.ParsedDate;
import com.cardIndex.domain.entity.Dates;
import com.cardIndex.domain.entity.DatesFree;
import com.cardIndex.domain.enums.DateType;
import com.cardIndex.repository.DatesFreeRepostiory;
import com.cardIndex.repository.DatesRepository;
import com.cardIndex.services.DatesService;
import com.cardIndex.services.mappers.DatesMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class DatesServiceImpl implements DatesService {

    private final DatesRepository datesRepository;
    private final DatesFreeRepostiory datesFreeRepostiory;

    public DatesServiceImpl(DatesRepository datesRepository,
                            DatesFreeRepostiory datesFreeRepostiory) {
        this.datesRepository = datesRepository;
        this.datesFreeRepostiory = datesFreeRepostiory;
    }

    @Override
    public List<ParsedDate> getAllDatesByEmployeeIdWithFreeDates(final Long employeeId) {
        List<DatesDto> freeDates = DatesMapper.INSTANCE.freeDatesListToListDto(this.datesFreeRepostiory.findAll());
        List<DatesDto> datesById = DatesMapper.INSTANCE.datesListDtoToDates(this.datesRepository.findByEmployeeId(employeeId));
        datesById.addAll(freeDates);
        Calendar calendar = Calendar.getInstance();

        return datesById.stream().map(value -> {
            calendar.setTime(value.getDate());
            ParsedDate parsedDate = new ParsedDate();
            parsedDate.setId(value.getId());
            parsedDate.setDay(calendar.get(Calendar.DAY_OF_MONTH));
            parsedDate.setMonth(calendar.get(Calendar.MONTH));
            parsedDate.setYear(calendar.get(Calendar.YEAR));
            parsedDate.setDateType(value.getDateType().name());

            return parsedDate;
        })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateDates(final List<DatesDto> datesDto, final Integer employeeId) {
        List<Dates> changesDates = DatesMapper.INSTANCE.datesListToDatesDto(datesDto);
        List<Dates> datesFromData = this.datesRepository.findByEmployeeId(employeeId);
        
//        Delete all unnessery dates
        List<Long> datesToDelete = changesDates.stream().filter(date -> date.getDateType().equals(DateType.Delete))
                .map(Dates::getId)
                .collect(Collectors.toList());

        datesToDelete.forEach(this.datesRepository::deleteById);

        List<Dates> datesToSave = new ArrayList<>();
        if (datesFromData.size() == 0) {
            datesToSave = changesDates;
        } else {
            List<Dates> finalDatesToSave = datesToSave;
            datesFromData.forEach(date -> {
                changesDates.forEach(changeDate -> {
                    if (changeDate.getDate().equals(new Timestamp(date.getDate().getTime())) && !changeDate.getDateType().equals(DateType.Delete)) {
                        date.setDate(changeDate.getDate());
                        date.setDateType(changeDate.getDateType());
                        finalDatesToSave.add(date);
                    } else {
                        if (!changeDate.getDateType().equals(DateType.Delete)) {
                            finalDatesToSave.add(changeDate);
                        }
                    }
                });
            });
        }

        if(datesToSave.size() > 0 ) {
            this.datesRepository.saveAll(datesToSave);
        }
    }

    @Override
    public void updateFreeDates(final List<DatesFree> datesFrees) {
        this.datesFreeRepostiory.saveAll(datesFrees);
    }

    @Override
    public void deleteDate(final Long dateId) {
        this.datesRepository.deleteById(dateId);
    }

}


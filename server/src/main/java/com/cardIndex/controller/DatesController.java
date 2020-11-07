package com.cardIndex.controller;

import com.cardIndex.domain.dto.DatesDto;
import com.cardIndex.domain.dto.ParsedDate;
import com.cardIndex.domain.entity.Dates;
import com.cardIndex.domain.entity.DatesFree;
import com.cardIndex.domain.enums.FileType;
import com.cardIndex.services.DatesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/dates")
@CrossOrigin(origins = "*")
public class DatesController {
    private DatesService datesService;

    public DatesController(DatesService datesService) {
        this.datesService = datesService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ParsedDate> getAllDatesByEmployeeIdWithFreeDates(@RequestParam long employeeId) {
        return this.datesService.getAllDatesByEmployeeIdWithFreeDates(employeeId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateDate(@RequestBody List<DatesDto> datesDto, @RequestParam Integer employeeId) {
        this.datesService.updateDates(datesDto, employeeId);
    }

    @PostMapping("/freeDates")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateFreeDates(@RequestBody List<DatesFree> datesFrees) {
        this.datesService.updateFreeDates(datesFrees);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteDate(@RequestParam long dateId) {
        this.datesService.deleteDate(dateId);
    }

}

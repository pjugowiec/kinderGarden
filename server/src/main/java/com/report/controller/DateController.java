package com.report.controller;

import com.report.domain.model.DateData;
import com.report.service.DateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Collection;

import static com.common.model.ResourceUrl.DATE;

@RestController
@RequestMapping(DATE)
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class DateController {

    private final DateService dateService;

    @GetMapping("/{id}")
    public ResponseEntity<Collection<DateData>> getDatesForEmployee(@PathParam("id") final Long employeeId) {
        return ResponseEntity.ok().body(dateService.getDatesForEmployee(employeeId));
    }


//    @PutMapping
//    public ResponseEntity<Void> updateDate(@RequestBody final List<DatesDto> datesDto,
//                                           @RequestParam final Integer employeeId) {
//        this.dateService.updateDates(datesDto, employeeId);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .build();
//    }

//    @PostMapping("/freeDates")
//    public ResponseEntity<Void> updateFreeDates(@RequestBody final List<DatesFree> datesFrees) {
//        this.dateService.updateFreeDates(datesFrees);
//
//        return ResponseEntity
//                .status(HttpStatus.ACCEPTED)
//                .build();
//    }
//
//    @DeleteMapping
//    public ResponseEntity<Void> deleteDate(@RequestParam final long dateId) {
//        this.dateService.deleteDate(dateId);
//
//        return ResponseEntity
//                .status(HttpStatus.ACCEPTED)
//                .build();
//    }

}

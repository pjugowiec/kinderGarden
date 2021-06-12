package com.raport.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dates")
@CrossOrigin(origins = "*")
public class DatesController {

//    private final DatesService datesService;
//
//    public DatesController(DatesService datesService) {
//        this.datesService = datesService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<ParsedDate>> getAllDatesByEmployeeIdWithFreeDates(@RequestParam final long employeeId) {
//
//        return ResponseEntity
//                .status(HttpStatus.ACCEPTED)
//                .body(this.datesService.getAllDatesByEmployeeIdWithFreeDates(employeeId));
//    }
//
//    @PutMapping
//    public ResponseEntity<Void> updateDate(@RequestBody final List<DatesDto> datesDto,
//                                           @RequestParam final Integer employeeId) {
//        this.datesService.updateDates(datesDto, employeeId);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .build();
//    }

//    @PostMapping("/freeDates")
//    public ResponseEntity<Void> updateFreeDates(@RequestBody final List<DatesFree> datesFrees) {
//        this.datesService.updateFreeDates(datesFrees);
//
//        return ResponseEntity
//                .status(HttpStatus.ACCEPTED)
//                .build();
//    }
//
//    @DeleteMapping
//    public ResponseEntity<Void> deleteDate(@RequestParam final long dateId) {
//        this.datesService.deleteDate(dateId);
//
//        return ResponseEntity
//                .status(HttpStatus.ACCEPTED)
//                .build();
//    }

}

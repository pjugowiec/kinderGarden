package com.report.controller;

import com.report.domain.model.DateData;
import com.report.service.DateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Collection<DateData>> getDatesForEmployee(@PathVariable("id") final Long id) {
        return ResponseEntity.ok().body(dateService.getDatesForEmployee(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDatesForEmployee(@RequestBody final Collection<DateData> dates,
                                                       @PathVariable("id") final Long id) {
        dateService.updateDatesForEmployee(dates, id);
        return ResponseEntity.ok().build();
    }
}

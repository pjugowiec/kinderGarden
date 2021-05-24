package com.raport.controller;

import com.raport.domain.model.EmployeeDto;
import com.raport.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<Collection<EmployeeDto>> getEmployees(){

        return ResponseEntity
                .ok()
                .body(this.employeeService.getAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable("id") final Long employeeId) {

        return ResponseEntity
                .ok()
                .body(this.employeeService.findEmployeeByID(employeeId));
    }

    @PostMapping
    public ResponseEntity<Void> createEmployee(@RequestBody final EmployeeDto employeeDto){
        this.employeeService.createEmployee(employeeDto);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@RequestBody final EmployeeDto employeeDto, @PathVariable("id")  final Long employeeId) {
        this.employeeService.updateEmployee(employeeDto, employeeId);

        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") final Long employeeId) {
        this.employeeService.deleteEmployee(employeeId);

        return ResponseEntity
                .ok()
                .build();
    }

}

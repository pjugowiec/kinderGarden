package com.raport.controller;

import com.raport.domain.dto.EmployeeDto;
import com.raport.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(this.employeeService.getAll());
    }

    @PostMapping
    public ResponseEntity<Void> createEmployee(@RequestBody final EmployeeDto employeeDto){
        this.employeeService.createEmployee(employeeDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> updateEmployee(@RequestBody final EmployeeDto employeeDto, @PathVariable final long id) throws Exception {
        this.employeeService.updateEmployee(employeeDto, id);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteEmployee(@PathVariable final long id) {
        this.employeeService.deleteEmployee(id);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable final long id) throws Exception {

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(this.employeeService.findEmployeeByID(id));
    }

}

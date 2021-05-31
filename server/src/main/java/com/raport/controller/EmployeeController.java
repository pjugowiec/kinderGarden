package com.raport.controller;

import java.util.Collection;

import com.raport.domain.model.Employee;
import com.raport.domain.model.EmployeeTable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raport.services.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(final EmployeeService employeeService) {
	    this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<Collection<EmployeeTable>> getEmployees() {

	return ResponseEntity.ok(employeeService.getEmployees());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable("id") final Long employeeId) {
//
//	return ResponseEntity.ok(employeeService.findById(employeeId));
//    }
//
//    @PostMapping
//    public ResponseEntity<Void> createEmployee(@RequestBody final EmployeeDto employeeDto) {
//	employeeService.createEmployee(employeeDto);
//
//	return ResponseEntity.ok().build();
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> updateEmployee(@RequestBody final EmployeeDto employeeDto,
//	    @PathVariable("id") final Long employeeId) {
//	employeeService.updateEmployee(employeeDto, employeeId);
//
//	return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") final Long employeeId) {
//	employeeService.deleteEmployee(employeeId);
//
//	return ResponseEntity.ok().build();
//    }

}

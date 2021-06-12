package com.raport.controller;

import java.util.Collection;

import com.raport.domain.model.employee.EmployeeForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.raport.domain.model.employee.EmployeeTable;
import com.raport.service.EmployeeService;

import lombok.AllArgsConstructor;

import static com.common.model.ResourceUrl.EMPLOYEE;

@RestController
@RequestMapping(EMPLOYEE)
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Collection<EmployeeTable>> getEmployees() {

	return ResponseEntity.ok(employeeService.getEmployees());
    }

    @PostMapping
    public ResponseEntity<Void> createEmployee(@RequestBody final EmployeeForm employee) {
        employeeService.createEmployee(employee);

        return ResponseEntity.ok().build();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable("id") final Long employeeId) {
//
//	return ResponseEntity.ok(employeeService.findById(employeeId));
//    }
//

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

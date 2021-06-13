package com.report.controller;

import com.report.domain.model.employee.EmployeeForm;
import com.report.domain.model.employee.EmployeeTable;
import com.report.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeForm> findEmployeeById(@PathVariable("id") final Long employeeId) {

        return ResponseEntity.ok(employeeService.findById(employeeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") final Long employeeId) {
        employeeService.deleteEmployee(employeeId);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@RequestBody final EmployeeForm employee,
                                               @PathVariable("id") final Long id) {
        employeeService.updateEmployee(employee, id);

        return ResponseEntity.ok().build();
    }


}

package com.cardIndex.controller;

import com.cardIndex.domain.dto.EmployeeDto;
import com.cardIndex.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<EmployeeDto> getAllEmployee(){
        return this.employeeService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@RequestBody EmployeeDto employeeDto){
        this.employeeService.createEmployee(employeeDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable long id) throws Exception {
        this.employeeService.updateEmployee(employeeDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteEmployee(@PathVariable long id) {
        this.employeeService.deleteEmployee(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public EmployeeDto findEmployeeById(@PathVariable long id) throws Exception {
        return this.employeeService.findEmployeeByID(id);
    }

}

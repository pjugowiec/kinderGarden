package com.raport.service.impl;

import com.raport.domain.entity.EmployeeEntity;
import com.raport.domain.model.employee.EmployeeForm;
import com.raport.domain.model.employee.EmployeeTable;
import com.raport.mapper.EmployeeMapper;
import com.raport.repository.EmployeeRepository;
import com.raport.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public Collection<EmployeeTable> getEmployees() {
        return employeeRepository.findAllEmployees();
    }

    @Override
    public void createEmployee(final EmployeeForm employee) {
        final EmployeeEntity employeeEntity = employeeMapper.employeeFormToEmployeeEntity(employee);
        this.employeeRepository.save(employeeEntity);
    }

    // @Override
    // public void updateEmployee(final EmployeeDto employee, final Long id) throws
    // Exception {
    // Employee employeeFromData = this.employeeRepository.findById(id)
    // .orElseThrow(Exception::new);
    // employeeFromData.setId(id);
    // employeeFromData.setCountOfChildrenCare(employee.getCountOfChildrenCare());
    // employeeFromData.setCountOfVacation(employee.getCountOfVacation());
    // employeeFromData.setName(employee.getName());
    // employeeFromData.setPosition(employee.getPosition());
    // employeeFromData.setRegularPost(employee.getRegularPost());
    // this.employeeRepository.save(employeeFromData);
    // }
    //
    // @Override
    // @Transactional
    // public void deleteEmployee(final Long id) {
    //
    // if (this.datesRepository.countByEmployeeId(id) != 0) {
    // this.datesRepository.deleteByEmployeeId(id);
    // this.employeeRepository.deleteById(id);
    // } else {
    // this.employeeRepository.deleteById(id);
    // }
    //
    // }
    //
    // @Override
    // public EmployeeDto findEmployeeByID(final Long id) throws Exception {
    // Employee employeeFromData = this.employeeRepository.findById(id)
    // .orElseThrow(Exception::new);
    // return EmployeeMapperImpl.INSTANCE.employeeToEmployeeDto(employeeFromData);
    // }
}

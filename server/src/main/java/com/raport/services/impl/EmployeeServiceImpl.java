package com.raport.services.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.raport.domain.model.employee.EmployeeTable;
import com.raport.repository.EmployeeRepository;
import com.raport.services.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Collection<EmployeeTable> getEmployees() {
	return employeeRepository.findAllBy();
    }

//    @Override
//    public void createEmployee(final EmployeeDto employee) {
//        Employee employeeToSave = EmployeeMapper.INSTANCE.employeeDtoToEmployee(employee);
//        this.employeeRepository.save(employeeToSave);
//
//    }
//
//    @Override
//    public void updateEmployee(final EmployeeDto employee, final Long id) throws Exception {
//        Employee employeeFromData = this.employeeRepository.findById(id)
//                .orElseThrow(Exception::new);
//        employeeFromData.setId(id);
//        employeeFromData.setCountOfChildrenCare(employee.getCountOfChildrenCare());
//        employeeFromData.setCountOfVacation(employee.getCountOfVacation());
//        employeeFromData.setName(employee.getName());
//        employeeFromData.setPosition(employee.getPosition());
//        employeeFromData.setRegularPost(employee.getRegularPost());
//        this.employeeRepository.save(employeeFromData);
//    }
//
//    @Override
//    @Transactional
//    public void deleteEmployee(final Long id) {
//
//        if (this.datesRepository.countByEmployeeId(id) != 0) {
//            this.datesRepository.deleteByEmployeeId(id);
//            this.employeeRepository.deleteById(id);
//        } else {
//            this.employeeRepository.deleteById(id);
//        }
//
//    }
//
//    @Override
//    public EmployeeDto findEmployeeByID(final Long id) throws Exception {
//        Employee employeeFromData = this.employeeRepository.findById(id)
//                .orElseThrow(Exception::new);
//        return EmployeeMapper.INSTANCE.employeeToEmployeeDto(employeeFromData);
//    }
}

package com.cardIndex.services.impl;

import com.cardIndex.domain.dto.EmployeeDto;
import com.cardIndex.domain.entity.Employee;
import com.cardIndex.repository.DatesRepository;
import com.cardIndex.repository.EmployeeRepository;
import com.cardIndex.services.EmployeeService;
import com.cardIndex.services.mappers.EmployeeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DatesRepository datesRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DatesRepository datesRepository) {
        this.employeeRepository = employeeRepository;
        this.datesRepository = datesRepository;
    }

    @Override
    public List<EmployeeDto> getAll() {
        return EmployeeMapper.INSTANCE.employeeToEmployeeDtos(this.employeeRepository.findAll());
    }

    @Override
    public void createEmployee(EmployeeDto employee) {
        Employee employeeToSave = EmployeeMapper.INSTANCE.employeeDtoToEmployee(employee);
        this.employeeRepository.save(employeeToSave);

    }

    @Override
    public void updateEmployee(EmployeeDto employee, long id) throws Exception {
        Employee employeeFromData = this.employeeRepository.findById(id)
                .orElseThrow(Exception::new);
        employeeFromData.setId(id);
        employeeFromData.setCountOfChildrenCare(employee.getCountOfChildrenCare());
        employeeFromData.setCountOfVacation(employee.getCountOfVacation());
        employeeFromData.setName(employee.getName());
        employeeFromData.setPosition(employee.getPosition());
        employeeFromData.setRegularPost(employee.getRegularPost());
        this.employeeRepository.save(employeeFromData);
    }

    @Override
    @Transactional
    public void deleteEmployee(long id) {

        if (this.datesRepository.countByEmployeeId(id) != 0) {
            this.datesRepository.deleteByEmployeeId(id);
            this.employeeRepository.deleteById(id);
        } else {
            this.employeeRepository.deleteById(id);
        }

    }

    @Override
    public EmployeeDto findEmployeeByID(long id) throws Exception {
        Employee employeeFromData = this.employeeRepository.findById(id)
                .orElseThrow(Exception::new);
        return EmployeeMapper.INSTANCE.employeeToEmployeeDto(employeeFromData);
    }
}

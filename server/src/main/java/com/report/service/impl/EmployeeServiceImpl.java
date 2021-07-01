package com.report.service.impl;

import com.common.exception.NotFoundException;
import com.common.model.ErrorMessage;
import com.report.domain.entity.EmployeeEntity;
import com.report.domain.model.employee.EmployeeForm;
import com.report.domain.model.employee.EmployeeTable;
import com.report.mapper.EmployeeMapper;
import com.report.repository.EmployeeRepository;
import com.report.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

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

    @Override
    public EmployeeForm findById(final Long id) {
        return employeeRepository.findEmployeeById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.EMP01));
    }

    @Transactional
    @Override
    public void deleteEmployee(final Long employeeId) {
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(employeeId);

        if(optionalEmployeeEntity.isEmpty()) throw new NotFoundException(ErrorMessage.EMP01);

        employeeRepository.deleteById(employeeId);

    }

    @Transactional
    @Override
    public void updateEmployee(final EmployeeForm employee, final Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.EMP01));

        employeeEntity = employeeMapper.updateEmployeeEntityByEmployeeForm(employeeEntity, employee);

        employeeRepository.save(employeeEntity);

    }

    @Override
    public EmployeeEntity getEmployeeWithDates(final Long id, final Integer year) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findEmployeeWithDatesById(id, year);

        if(employeeEntity.isEmpty()) throw new NotFoundException(ErrorMessage.EMP01);

        return employeeEntity.get();
    }
}

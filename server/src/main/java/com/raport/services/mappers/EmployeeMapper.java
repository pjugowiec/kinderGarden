package com.raport.services.mappers;

import com.raport.domain.model.EmployeeDto;
import com.raport.domain.entity.Employee;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);


    @Mapping(target = "datesDto", ignore = true)
    EmployeeDto employeeToEmployeeDto(Employee employee);


    @Mapping(target = "dates", ignore = true)
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> employeeToEmployeeDtos(List<Employee> employees);

    List<Employee> employeeDtosToEmployee(List<EmployeeDto> employeeDto);
}

package com.cardIndex.services.mappers;

import com.cardIndex.domain.dto.DatesDto;
import com.cardIndex.domain.dto.EmployeeDto;
import com.cardIndex.domain.entity.Dates;
import com.cardIndex.domain.entity.DatesFree;
import com.cardIndex.domain.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface DatesMapper {

    DatesMapper INSTANCE = Mappers.getMapper(DatesMapper.class);


    @Mapping(target = "employeeDto", source = "employee")
    DatesDto datesToDatesDto(Dates dates);

    @Mapping(target = "employee", source = "employeeDto")
    Dates datesDtoToDates(DatesDto datesDto);

    @Mapping(target = "datesDto", ignore = true)
    @Mapping(target = "id", source = "id")
    EmployeeDto employeeToEmployeeDto(Employee employee);


    @Mapping(target = "dates", source = "datesDto")
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    List<DatesDto> datesListDtoToDates(List<Dates> dates);
    List<Dates> datesListToDatesDto(List<DatesDto> datesDto);

    List<DatesDto> freeDatesListToListDto(List<DatesFree> datesFrees);

    @Mapping(target = "employeeDto", ignore = true)
    @Mapping(target = "date", source = "freeDate")
    DatesDto freeDatesToDatesDto(DatesFree datesFree);
}

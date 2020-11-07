package com.cardIndex.domain.dto;

import com.cardIndex.domain.enums.DateType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class DatesDto {

    private long id;
    private Date date;
    private DateType dateType;
    private EmployeeDto employeeDto;
}

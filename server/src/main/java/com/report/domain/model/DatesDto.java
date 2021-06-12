package com.report.domain.model;

import com.report.domain.enums.DateType;
import lombok.Data;

import java.util.Date;

@Data
public class DatesDto {

    private long id;
    private Date date;
    private DateType dateType;
//    private EmployeeDto employeeDto;
}

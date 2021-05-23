package com.raport.domain.dto.internal;

import com.raport.domain.entity.Dates;
import com.raport.domain.entity.Employee;
import lombok.Data;

import java.util.List;

@Data
public class GenerateExcelDtoInternal {

    private List<Dates> dates;
    private String nameToPrint;
    private Employee employee;
}

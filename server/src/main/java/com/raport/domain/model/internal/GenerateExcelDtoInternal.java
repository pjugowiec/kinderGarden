package com.raport.domain.model.internal;

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

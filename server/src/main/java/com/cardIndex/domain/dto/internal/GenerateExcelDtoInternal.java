package com.cardIndex.domain.dto.internal;

import com.cardIndex.domain.entity.Dates;
import com.cardIndex.domain.entity.Employee;
import lombok.Data;

import java.util.List;

@Data
public class GenerateExcelDtoInternal {

    private List<Dates> dates;
    private String nameToPrint;
    private Employee employee;
}

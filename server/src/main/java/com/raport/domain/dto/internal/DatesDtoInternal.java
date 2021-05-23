package com.raport.domain.dto.internal;

import com.raport.domain.enums.DateType;
import lombok.Data;

@Data
public class DatesDtoInternal {

    private int day;
    private int month;
    private DateType dateType;
}

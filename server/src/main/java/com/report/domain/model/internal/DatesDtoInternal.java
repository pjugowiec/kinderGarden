package com.report.domain.model.internal;

import com.report.domain.enums.DateType;
import lombok.Data;

@Data
public class DatesDtoInternal {

    private int day;
    private int month;
    private DateType dateType;
}

package com.cardIndex.domain.dto.internal;

import com.cardIndex.domain.enums.DateType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class DatesDtoInternal {

    private int day;
    private int month;
    private DateType dateType;
}

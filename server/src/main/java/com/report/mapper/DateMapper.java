package com.report.mapper;

import com.report.domain.entity.DateEntity;
import com.report.domain.model.DateData;

import java.util.Collection;

public interface DateMapper {

    DateEntity dateDataToDateEntity(final DateData dateData);
    Collection<DateEntity> datesDataToDateEntities(final Collection<DateData> dateData);
}

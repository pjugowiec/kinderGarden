package com.report.domain.model.excel;

import com.common.exception.ExcelException;
import com.common.model.ErrorMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import static com.common.model.ErrorMessage.putValuesIntoMessage;
import static com.common.util.CommonValue.PATH_TO_EXCEL_TEMPLATES;

@NoArgsConstructor
@Getter
@Component
public final class ReportConfiguration {

    private final Integer startMonthRowIndex = 9;
    private final Integer startDaysColumnIndex = 2;
    private final Integer summaryRowIndex = 33;
    private final String defaultSheetName = "REPORT_RECORD";

    @Getter
    @Value(PATH_TO_EXCEL_TEMPLATES + "REPORT_WORKER_TEMPLATE.xlsx")
    private Resource resource;

    /**
     * Get the index of the month, multiply by two because:
     * | I           |
     * | line skip   |
     * | II          |
     * | line skip   |
     * Minus two to get to the Row with "symbol", indexes in excel starting by 0
     *
     * @param month Start normal from 1 - January etc.
     * @return Index of selected month
     */
    public Integer getRowMonthIndex(final Integer month) {
        if (month > 31)
            throw new ExcelException(putValuesIntoMessage(ErrorMessage.C03, month, 12), ErrorMessage.C03.name());
        return (startMonthRowIndex + Math.multiplyExact(month, 2)) - 2;
    }

    /**
     * Get index of day column, minus one because indexes in excel started by 0
     *
     * @param day Normal format 1,2,3,4,5 ...
     * @return Index of selected day
     */
    public Integer getColumnDayIndex(final Integer day) {
        if (day > 31)
            throw new ExcelException(putValuesIntoMessage(ErrorMessage.C03, day, 31), ErrorMessage.C03.name());

        return (startDaysColumnIndex + day) - 1;
    }
}

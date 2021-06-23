package com.report.domain.model;

import com.common.model.ErrorMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.report.domain.enums.DateType;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DateData {

    @NotNull(message = ErrorMessage.AnnotationMessage.NULL_FIELD)
    private Long id;

    @NotNull(message = ErrorMessage.AnnotationMessage.NULL_FIELD)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull(message = ErrorMessage.AnnotationMessage.NULL_FIELD)
    private DateType type;

    @NotNull(message = ErrorMessage.AnnotationMessage.NULL_FIELD)
    private Long employeeId;

    public DateData(Long id, LocalDate date, DateType type) {
        this.id = id;
        this.date = date;
        this.type = type;
    }
}

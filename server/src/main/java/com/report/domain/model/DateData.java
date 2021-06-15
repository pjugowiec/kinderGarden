package com.report.domain.model;

import com.common.model.ErrorMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.report.domain.enums.DateType;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DateData {

    @NotNull(message = ErrorMessage.AnnotationMessage.NULL_FIELD)
    private Long id;

    @NotNull(message = ErrorMessage.AnnotationMessage.NULL_FIELD)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date;

    @NotNull(message = ErrorMessage.AnnotationMessage.NULL_FIELD)
    private DateType type;
}

package com.report.domain.model.employee;

import com.common.model.ErrorMessage;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Employee {

    private Long id;

    @NotNull(message = ErrorMessage.NULL_FIELD)
    @Size(message = ErrorMessage.THREE_LENGTH)
    private String name;

    @NotNull(message = ErrorMessage.NULL_FIELD)
    @Size(message = ErrorMessage.THREE_LENGTH)
    private String lastname;

    @NotNull(message = ErrorMessage.NULL_FIELD)
    @Size(message = ErrorMessage.THREE_LENGTH)
    private String regularPost;

    @NotNull(message = ErrorMessage.NULL_FIELD)
    @Min(value = 0, message = ErrorMessage.MINIMUM_ZERO)
    private Integer countOfVacation;

    @NotNull(message = ErrorMessage.NULL_FIELD)
    @Min(value = 0, message = ErrorMessage.MINIMUM_ZERO)
    private Integer countOfChildrenCare;

    @NotNull(message = ErrorMessage.NULL_FIELD)
    private String position;
}

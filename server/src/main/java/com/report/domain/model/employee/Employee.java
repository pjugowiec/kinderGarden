package com.report.domain.model.employee;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Employee {

    private Long id;
    private String name;
    private String lastname;
    private String regularPost;
    private Integer countOfVacation;
    private Integer countOfChildrenCare;
    private String position;
}
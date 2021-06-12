package com.raport.domain.model.employee;

import lombok.*;

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

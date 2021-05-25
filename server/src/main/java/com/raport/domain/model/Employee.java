package com.raport.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Employee {

    private Long id;
    private String name;
    private String lastName;
    private String regularPost;
    private String position;
    private Integer countOfVacation;
    private Integer countOfChildrenCare;

}

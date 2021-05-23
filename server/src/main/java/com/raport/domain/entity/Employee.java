package com.raport.domain.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String lastName;

    private String regularPost;

    @OneToMany(mappedBy = "employee")
    @JsonManagedReference
    private List<Dates> dates;

    private int countOfVacation;
    private int countOfChildrenCare;
    private String position;

}

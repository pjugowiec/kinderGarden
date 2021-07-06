package com.report.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employees")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastname;

    @Column(name = "regular_post", nullable = false)
    private String regularPost;

    @Column(name = "count_of_vacation", nullable = false)
    private int countOfVacation;

    @Column(name = "count_of_children_care", nullable = false)
    private int countOfChildrenCare;

    @Column(name = "position", nullable = false)
    private String position;

    @OneToMany(mappedBy = "employee")
    private Set<DateEntity> dates;

}

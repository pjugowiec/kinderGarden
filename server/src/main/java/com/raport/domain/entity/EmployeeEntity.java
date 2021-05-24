package com.raport.domain.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "regular_post", nullable = false)
    private String regularPost;

    @Column(name = "count_of_vacation", nullable = false)
    private int countOfVacation;

    @Column(name = "count_of_children_care", nullable = false)
    private int countOfChildrenCare;

    @Column(name = "position", nullable = false)
    private String position;

    @OneToMany(mappedBy = "employee")
    private Set<Dates> dates;

    @Override
    public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (o == null || getClass() != o.getClass())
	    return false;
	EmployeeEntity that = (EmployeeEntity) o;
	return id == that.id && countOfVacation == that.countOfVacation
		&& countOfChildrenCare == that.countOfChildrenCare && name.equals(that.name)
		&& lastName.equals(that.lastName) && regularPost.equals(that.regularPost)
		&& position.equals(that.position);
    }

    @Override
    public int hashCode() {
	return Objects.hash(id, name, lastName, regularPost, countOfVacation, countOfChildrenCare, position);
    }
}

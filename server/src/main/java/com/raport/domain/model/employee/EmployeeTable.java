package com.raport.domain.model.employee;

public class EmployeeTable extends Employee {

    public EmployeeTable(Long id, String name, String lastname, String regularPost, Integer countOfVacation,
	    Integer countOfChildrenCare, String position) {
	super(id, name, lastname, regularPost, countOfVacation, countOfChildrenCare, position);
    }
}

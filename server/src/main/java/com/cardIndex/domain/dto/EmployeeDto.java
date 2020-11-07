package com.cardIndex.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeDto {

    private long id;
    private String name;
    private String regularPost;
    private List<DatesDto> datesDto;
    private int countOfVacation;
    private int countOfChildrenCare;
    private String position;
}

package com.raport.domain.dto;

import lombok.Data;

@Data
public class ParsedDate {

    private long id;
    private int year;
    private int month;
    private int day;
    private String dateType;
}

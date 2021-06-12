package com.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessages {

    C01("Internal exception"),

    EMP01("Employee not exists");

    private final String value;
}

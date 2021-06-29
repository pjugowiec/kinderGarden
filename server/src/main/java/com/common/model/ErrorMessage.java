package com.common.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum ErrorMessage {

    C01("Internal exception"),

    EMP01("Employee not exists"),

    EXL01("Not found initial excel file!"),

    V00(AnnotationMessage.NULL_FIELD),
    V01(AnnotationMessage.MINIMUM_ZERO),
    V02(AnnotationMessage.THREE_LENGTH);

    @Getter
    private final String value;

    public static ErrorMessage findByMessage(final String message) {
        return message == null ? ErrorMessage.C01 :
                Arrays.stream(ErrorMessage.values())
                        .filter(v -> v.getValue().equals(message))
                        .findFirst()
                        .orElse(ErrorMessage.C01);
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class AnnotationMessage {
        public static final String NULL_FIELD = "Value must not be null";
        public static final String THREE_LENGTH = "Value must have more minimum 3 characters";
        public static final String MINIMUM_ZERO = "Value must be zero or bigger";
    }
}

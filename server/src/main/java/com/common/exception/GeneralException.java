package com.common.exception;

import com.common.model.ErrorMessages;
import lombok.Getter;

public class GeneralException extends RuntimeException{

    @Getter
    private String errorCode;

    public GeneralException(final ErrorMessages errorMessages) {
        super(errorMessages.getValue());
    }

    public GeneralException(final String message, final String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}

package com.common.exception;

import com.common.model.ErrorMessages;
import lombok.Getter;

public class GeneralException extends RuntimeException {

    @Getter
    private final String errorCode;

    public GeneralException(final String message, final String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public GeneralException(final String message, final String errorCode, Throwable e) {
        super(message, e);
        this.errorCode = errorCode;
    }

    public GeneralException(final ErrorMessages errorMessages) {
        super(errorMessages.getValue());
        this.errorCode = errorMessages.name();
    }

}

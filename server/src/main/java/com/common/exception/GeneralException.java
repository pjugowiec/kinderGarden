package com.common.exception;

import com.common.model.ErrorMessage;
import lombok.Getter;

public class GeneralException extends RuntimeException {

    @Getter
    private final String errorCode;

    public GeneralException(final String message, final String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public GeneralException(final ErrorMessage errorMessage) {
        super(errorMessage.getValue());
        this.errorCode = errorMessage.name();
    }

}

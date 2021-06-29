package com.common.exception;

import com.common.model.ErrorMessage;

public class ValidatorException extends GeneralException {

    public ValidatorException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }

    public ValidatorException(final String message, final String errorCode) {
        super(message, errorCode);
    }
}

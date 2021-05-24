package com.common.exception;

import com.common.model.ErrorMessages;

public class ValidatorException extends GeneralException {

    public ValidatorException(ErrorMessages errorMessages) {
        super(errorMessages);
    }

    public ValidatorException(String message) {
        super(message);
    }
}

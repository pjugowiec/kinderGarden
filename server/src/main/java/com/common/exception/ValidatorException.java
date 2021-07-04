package com.common.exception;

import com.common.model.ErrorMessage;

public class ValidatorException extends GeneralException{

    public ValidatorException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

    public ValidatorException(String message, String errorCode) {
        super(message, errorCode);
    }
}

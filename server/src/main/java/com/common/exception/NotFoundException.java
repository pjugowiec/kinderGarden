package com.common.exception;

import com.common.model.ErrorMessages;

public class NotFoundException extends GeneralException{

    public NotFoundException(ErrorMessages errorMessages) {
        super(errorMessages);
    }
}

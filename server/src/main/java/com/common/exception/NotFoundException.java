package com.common.exception;

import com.common.model.ErrorMessage;

public class NotFoundException extends GeneralException{

    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}

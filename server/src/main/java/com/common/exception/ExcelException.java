package com.common.exception;

import com.common.model.ErrorMessage;

public class ExcelException extends GeneralException{

    public ExcelException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}

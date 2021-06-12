package com.common.config;

import com.common.exception.GeneralException;
import com.common.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

public class ApiHandler {

    @ExceptionHandler({GeneralException.class, Exception.class})
    public ResponseEntity<ErrorResponse> handleInternalException(GeneralException exception) {
        final ErrorResponse errorResponse =
                createErrorResponse(exception.getMessage(), exception.getErrorCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Create a model to return errors
     *
     * @param message Error message from exception
     * @param errorCode Name of {@link com.common.model.ErrorMessages} eg. C01
     * @param httpStatus {@link HttpStatus}
     * @return Model with parsed info about exception
     */
    private ErrorResponse createErrorResponse(final String message,final String errorCode, final HttpStatus httpStatus){
        return ErrorResponse.builder()
                .status(httpStatus.value())
                .message(message)
                .errorCode(errorCode)
                .time(LocalDateTime.now())
                .build();
    }
}

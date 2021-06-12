package com.common.config;

import com.common.exception.GeneralException;
import com.common.exception.NotFoundException;
import com.common.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiHandler {


    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundException(final NotFoundException exception) {
        final ErrorResponse errorResponse =
                createErrorResponse(exception.getMessage(), exception.getErrorCode(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({GeneralException.class, Exception.class})
    public ResponseEntity<ErrorResponse> handleInternalException(final GeneralException exception) {
        final ErrorResponse errorResponse =
                createErrorResponse(exception.getMessage(), exception.getErrorCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Create a model to return errors
     *
     * @param message    Error message from exception
     * @param errorCode  Name of {@link com.common.model.ErrorMessages} eg. C01
     * @param httpStatus {@link HttpStatus}
     * @return Model with parsed info about exception
     */
    private ErrorResponse createErrorResponse(final String message, final String errorCode, final HttpStatus httpStatus) {
        return ErrorResponse.builder()
                .httpStatus(httpStatus)
                .message(message)
                .errorCode(errorCode)
                .time(LocalDateTime.now())
                .build();
    }
}

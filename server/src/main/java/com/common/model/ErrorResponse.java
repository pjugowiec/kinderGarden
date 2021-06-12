package com.common.model;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@JsonPropertyOrder(value = { "time", "httpStatus", "message", "errorCode" })
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private HttpStatus httpStatus;
    private LocalDateTime time;
    private String message;
    private String errorCode;
}

package com.common.model;


import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private Integer status;
    private LocalDateTime time;
    private String message;
    private String errorCode;
}

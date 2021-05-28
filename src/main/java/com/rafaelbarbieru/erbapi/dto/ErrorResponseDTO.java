package com.rafaelbarbieru.erbapi.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorResponseDTO {

    private HttpStatus status;
    private String message;
    private String advice;

}

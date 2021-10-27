package com.anpora.erbhub.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Bean class for an application error
 */
@Data
@Builder
public class ErrorResponseDTO {

    private HttpStatus status;
    private String message;
    private String advice;

}

package com.anpora.erbhub.controllers.api;

import com.anpora.erbhub.dto.ErrorResponseDTO;
import com.anpora.erbhub.exceptions.BadRequestException;
import com.anpora.erbhub.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 * Class that handles all exceptions in the application
 */
@RestControllerAdvice
public class ExceptionHandlerController {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @Autowired
    private Environment env;

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadRequestException() {
        return createResponse(
                HttpStatus.BAD_REQUEST,
                env.getProperty("error.message.badrequest"),
                env.getProperty("error.advice.badrequest")
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException() {
        return createResponse(
                HttpStatus.NOT_FOUND,
                env.getProperty("error.message.notfound"),
                env.getProperty("error.advice.notfound")
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleUnknownException() {
        return createResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                env.getProperty("error.message.unexpectederror"),
                env.getProperty("error.advice.unexpectederror")
        );
    }

    private ResponseEntity<ErrorResponseDTO> createResponse(HttpStatus status, String message, String advice) {
        LOG.error(message);
        ErrorResponseDTO errorResponseBean = ErrorResponseDTO.builder()
                .status(status)
                .message(message)
                .advice(advice)
                .build();
        return new ResponseEntity<>(errorResponseBean, status);
    }

}

package com.chukcheck.api.common.exception;

import com.chukcheck.api.common.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<?> badRequest(IllegalArgumentException e) {
        log.error("BadRequest - IllegalArgumentException : {}", e.getMessage());
        return ApiResponse.fail(BAD_REQUEST.value(), e.getMessage());
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<?> serverError(RuntimeException e) {
        log.error("ServerError - RuntimeException : {}", e.getMessage());
        return ApiResponse.fail(INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}

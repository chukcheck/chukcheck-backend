package com.chukcheck.api.common.exception;

public class ApiException extends RuntimeException {

    public ApiException(ApiError error) {
        super(error.message());
    }
}

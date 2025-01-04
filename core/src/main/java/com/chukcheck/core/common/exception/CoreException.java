package com.chukcheck.core.common.exception;

public class CoreException extends RuntimeException {

    public CoreException(CoreError error) {
        super(error.getMessage());
    }
}

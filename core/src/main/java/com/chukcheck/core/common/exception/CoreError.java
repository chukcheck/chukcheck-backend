package com.chukcheck.core.common.exception;

public enum CoreError {

    NOT_FOUND_DOMAIN("도메인을 찾을 수 없습니다.");

    private final String message;

    CoreError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

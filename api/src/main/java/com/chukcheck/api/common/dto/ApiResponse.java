package com.chukcheck.api.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@JsonInclude(NON_NULL)
public class ApiResponse<T> {

    private final int code;
    private final String message;
    private T result;

    private ApiResponse() {
        this.code = HttpStatus.OK.value();
        this.message = HttpStatus.OK.getReasonPhrase();
    }

    private ApiResponse(T result) {
        this.code = HttpStatus.OK.value();
        this.message = HttpStatus.OK.getReasonPhrase();
        this.result = result;
    }

    private ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ApiResponse<Void> success() {
        return new ApiResponse<>();
    }

    public static <T> ApiResponse<T> success(T result) {
        return new ApiResponse<>(result);
    }

    public static ApiResponse<Void> fail(int code, String message) {
        return new ApiResponse<>(code, message);
    }
}

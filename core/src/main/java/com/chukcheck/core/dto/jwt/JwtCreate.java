package com.chukcheck.core.dto.jwt;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class JwtCreate {

    private final String token;
    private final LocalDateTime expire;

    @Builder
    public JwtCreate(String token, LocalDateTime expire) {
        this.token = token;
        this.expire = expire;
    }
}

package com.chukcheck.core.dto.request.create;

import com.chukcheck.core.dto.jwt.JwtCreate;
import com.chukcheck.core.dto.jwt.JwtRole;
import com.chukcheck.core.entity.Token;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class TokenCreateRequest {

    @NotEmpty
    private Long subject;

    @NotEmpty
    private JwtRole scope;

    @Builder
    public TokenCreateRequest(Long subject, JwtRole scope) {
        this.subject = subject;
        this.scope = scope;
    }

    public Token toEntity(JwtCreate create) {
        return Token.builder()
                .subject(subject)
                .role(scope)
                .refreshToken(create.getToken())
                .expireDate(create.getExpire())
                .build();
    }
}

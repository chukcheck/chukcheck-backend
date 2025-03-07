package com.chukcheck.api.service;

import com.chukcheck.core.dto.jwt.JwtCreate;
import com.chukcheck.core.dto.jwt.JwtToken;
import com.chukcheck.core.dto.request.create.TokenCreateRequest;
import com.chukcheck.core.entity.Token;
import com.chukcheck.core.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.jsonwebtoken.lang.Assert.notNull;
import static java.time.LocalDateTime.now;
import static org.springframework.util.StringUtils.hasText;

@Service
@Transactional
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    @Transactional(readOnly = true)
    public void validateRefreshToken(JwtToken token, String refreshToken) {
        tokenRepository.findBySubjectAndRoleAndRefreshToken(token.getSubject(), token.getScope(), refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Not found refresh token"));
    }

    public Token create(TokenCreateRequest request, JwtCreate refresh) {
        validateRequest(request);
        validateJwt(refresh);

        return tokenRepository.save(request.toEntity(refresh));
    }

    private void validateRequest(TokenCreateRequest request) {
        notNull(request.getSubject(), "Required subject");
        notNull(request.getScope(), "Required scope");
    }

    private void validateJwt(JwtCreate refresh) {
        if (!hasText(refresh.getToken())) {
            throw new IllegalArgumentException("Required token");
        }

        if (refresh.getExpire().isBefore(now())) {
            throw new IllegalArgumentException("Invalid expire date");
        }
    }
}

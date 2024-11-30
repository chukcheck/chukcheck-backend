package com.chukcheck.api.service;

import com.chukcheck.api.config.jwt.JwtProvider;
import com.chukcheck.api.config.jwt.JwtUserDetails;
import com.chukcheck.core.dto.jwt.JwtCreate;
import com.chukcheck.core.dto.jwt.JwtToken;
import com.chukcheck.core.dto.jwt.JwtType;
import com.chukcheck.core.dto.request.create.TokenCreateRequest;
import com.chukcheck.core.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.chukcheck.core.dto.jwt.JwtRole.ROLE_ADMIN;
import static com.chukcheck.core.dto.jwt.JwtRole.ROLE_USER;
import static com.chukcheck.core.dto.jwt.JwtType.ACCESS;
import static com.chukcheck.core.dto.jwt.JwtType.REFRESH;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final JwtProvider provider;
    private final MemberService memberService;
    private final AdminService adminService;
    private final TokenService tokenService;

    public TokenResponse accessToken(TokenCreateRequest request) {
        isMember(request);
        isAdmin(request);

        JwtCreate access = createJwt(request, ACCESS);
        JwtCreate refresh = createJwt(request, REFRESH);

        save(request, refresh);

        return TokenResponse.builder()
                .accessToken(access.getToken())
                .accessTokenExpireDate(access.getExpire())
                .refreshToken(refresh.getToken())
                .refreshTokenExpireDate(refresh.getExpire())
                .build();
    }

    public TokenResponse refreshToken(JwtUserDetails principal) {
        TokenCreateRequest request = principal.toRequest();

        JwtCreate access = createJwt(request, ACCESS);
        JwtCreate refresh = createJwt(request, REFRESH);

        save(request, refresh);

        return TokenResponse.builder()
                .accessToken(access.getToken())
                .refreshToken(refresh.getToken())
                .accessTokenExpireDate(access.getExpire())
                .refreshTokenExpireDate(refresh.getExpire())
                .build();
    }

    private void save(TokenCreateRequest request, JwtCreate refresh) {
        tokenService.create(request, refresh);
    }

    private JwtCreate createJwt(TokenCreateRequest request, JwtType type) {
        return provider.createJwt(JwtToken.builder()
                .subject(request.getSubject())
                .scope(request.getScope())
                .type(type)
                .build());
    }

    private void isMember(TokenCreateRequest request) {
        if (ROLE_USER.equals(request.getScope())) memberService.read(request.getSubject());
    }

    private void isAdmin(TokenCreateRequest request) {
        if (ROLE_ADMIN.equals(request.getScope())) adminService.read(request.getSubject());
    }
}

package com.chukcheck.api.controller;

import com.chukcheck.api.config.jwt.JwtUserDetails;
import com.chukcheck.core.dto.request.create.TokenCreateRequest;
import com.chukcheck.core.dto.response.TokenResponse;
import com.chukcheck.core.dto.response.BaseResponse;
import com.chukcheck.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/access-token")
    public BaseResponse<TokenResponse> accessToken(@RequestBody TokenCreateRequest request) {
        return new BaseResponse<>(authService.accessToken(request));
    }

    @PostMapping("/refresh-token")
    public BaseResponse<TokenResponse> refreshToken(@AuthenticationPrincipal JwtUserDetails principal) {
        return new BaseResponse<>(authService.refreshToken(principal));
    }
}

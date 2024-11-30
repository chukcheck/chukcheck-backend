package com.chukcheck.api.controller;

import com.chukcheck.api.exception.AccessDeniedException;
import com.chukcheck.api.exception.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/authentication")
    public void authentication() {
        throw new AuthenticationException("Authentication failed");
    }

    @GetMapping("/access-denied")
    public void accessDenied() {
        throw new AccessDeniedException("Access denied");
    }
}

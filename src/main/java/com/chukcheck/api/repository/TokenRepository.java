package com.chukcheck.api.repository;

import com.chukcheck.api.dto.jwt.JwtRole;
import com.chukcheck.api.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findBySubjectAndRoleAndRefreshToken(Long subject, JwtRole role,String refreshToken);
}

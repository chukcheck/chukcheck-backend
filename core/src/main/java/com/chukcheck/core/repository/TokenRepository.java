package com.chukcheck.core.repository;

import com.chukcheck.core.dto.jwt.JwtRole;
import com.chukcheck.core.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findBySubjectAndRoleAndRefreshToken(Long subject, JwtRole role, String refreshToken);
}

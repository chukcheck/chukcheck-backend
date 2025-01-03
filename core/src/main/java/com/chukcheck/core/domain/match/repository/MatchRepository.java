package com.chukcheck.core.domain.match.repository;

import com.chukcheck.core.domain.match.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long>, MatchQueryRepository {
    
}

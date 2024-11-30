package com.chukcheck.core.repository;

import com.chukcheck.core.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long>, MatchQueryRepository {
    
}

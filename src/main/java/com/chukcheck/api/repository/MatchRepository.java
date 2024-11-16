package com.chukcheck.api.repository;

import com.chukcheck.api.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long>, MatchQueryRepository {
    
}

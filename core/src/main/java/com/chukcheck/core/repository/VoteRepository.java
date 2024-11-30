package com.chukcheck.core.repository;

import com.chukcheck.core.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long>, VoteQueryRepository {
    
}

package com.chukcheck.api.repository;

import com.chukcheck.api.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long>, VoteQueryRepository {
    
}

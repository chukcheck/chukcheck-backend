package com.chukcheck.core.domain.stadium.repository;

import com.chukcheck.core.domain.stadium.entity.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StadiumRepository extends JpaRepository<Stadium, Long> {
    
}

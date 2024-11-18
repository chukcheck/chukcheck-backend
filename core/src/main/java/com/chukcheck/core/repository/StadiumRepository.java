package com.chukcheck.core.repository;

import com.chukcheck.core.entity.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StadiumRepository extends JpaRepository<Stadium, Long> {
    
}

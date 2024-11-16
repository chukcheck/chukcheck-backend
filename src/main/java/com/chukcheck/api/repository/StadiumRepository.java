package com.chukcheck.api.repository;

import com.chukcheck.api.entity.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StadiumRepository extends JpaRepository<Stadium, Long> {
    
}

package com.chukcheck.core.repository;

import com.chukcheck.core.entity.Attend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendRepository extends JpaRepository<Attend, Long>, AttendQueryRepository {

}

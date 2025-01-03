package com.chukcheck.core.domain.attend.repository;

import com.chukcheck.core.domain.attend.entity.Attend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendRepository extends JpaRepository<Attend, Long>, AttendQueryRepository {

}

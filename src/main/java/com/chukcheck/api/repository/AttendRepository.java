package com.chukcheck.api.repository;

import com.chukcheck.api.entity.Attend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendRepository extends JpaRepository<Attend, Long>, AttendQueryRepository {

}

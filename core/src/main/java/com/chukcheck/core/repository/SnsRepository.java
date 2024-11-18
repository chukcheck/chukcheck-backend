package com.chukcheck.core.repository;

import com.chukcheck.core.entity.Sns;
import com.chukcheck.core.entity.SnsType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SnsRepository extends JpaRepository<Sns, Long>, SnsQueryRepository {

    Optional<Sns> findByUuidAndType(String uuid, SnsType type);
}

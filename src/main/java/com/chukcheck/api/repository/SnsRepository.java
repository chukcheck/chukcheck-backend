package com.chukcheck.api.repository;

import com.chukcheck.api.entity.Sns;
import com.chukcheck.api.entity.SnsType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SnsRepository extends JpaRepository<Sns, Long>, SnsQueryRepository {

    Optional<Sns> findByUuidAndType(String uuid, SnsType type);
}

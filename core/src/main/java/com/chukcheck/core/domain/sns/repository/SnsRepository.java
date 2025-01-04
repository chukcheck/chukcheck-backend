package com.chukcheck.core.domain.sns.repository;

import com.chukcheck.core.domain.sns.entity.Sns;
import com.chukcheck.core.domain.sns.model.SnsType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SnsRepository extends JpaRepository<Sns, Long>, SnsQueryRepository {

    Optional<Sns> findByUuidAndType(String uuid, SnsType type);
}

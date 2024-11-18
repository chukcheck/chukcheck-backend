package com.chukcheck.core.repository;

import com.chukcheck.core.entity.Team;
import com.chukcheck.core.repository.TeamQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long>, TeamQueryRepository {

    Optional<Team> findByName(String name);
}

package com.chukcheck.api.repository;

import com.chukcheck.api.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long>, PlayerQueryRepository {

    Optional<Player> findByMemberIdAndTeamId(Long memberId, Long teamId);
    
}

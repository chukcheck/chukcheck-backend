package com.chukcheck.core.repository;

import com.chukcheck.core.entity.Player;
import com.chukcheck.core.repository.PlayerQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long>, PlayerQueryRepository {

    Optional<Player> findByMemberIdAndTeamId(Long memberId, Long teamId);
    
}

package com.chukcheck.core.domain.player.repository;

import com.chukcheck.core.domain.player.command.PlayerSearchCommand;
import com.chukcheck.core.domain.player.entity.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerQueryRepository {

    List<Player> findQueryBySearch(PlayerSearchCommand command);

    Optional<Player> findQueryById(Long id);
}

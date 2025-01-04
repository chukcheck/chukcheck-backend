package com.chukcheck.core.domain.player.application;

import com.chukcheck.core.domain.player.command.PlayerSearchCommand;
import com.chukcheck.core.domain.player.entity.Player;

import java.util.List;

public interface PlayerReader {

    Player findById(Long id);

    Player findNullableByMemberIdAndTeamId(Long memberId, Long teamId);

    List<Player> findQueryBySearch(PlayerSearchCommand command);

    Player findQueryById(Long id);
}

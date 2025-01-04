package com.chukcheck.core.domain.attend.command;

import com.chukcheck.core.domain.attend.entity.Attend;
import com.chukcheck.core.domain.attend.model.AttendStatus;
import com.chukcheck.core.domain.match.entity.Match;
import com.chukcheck.core.domain.player.entity.Player;

public record AttendCreateCommand(
        Long playerId,
        Long matchId,
        AttendStatus status
) {

    public Attend toEntity(Player player, Match match) {
        return Attend.builder()
                .player(player)
                .match(match)
                .status(status)
                .build();
    }
}

package com.chukcheck.core.domain.vote.command;

import com.chukcheck.core.domain.match.entity.Match;
import com.chukcheck.core.domain.player.entity.Player;
import com.chukcheck.core.domain.vote.entity.Vote;
import com.chukcheck.core.domain.vote.model.VoteStatus;

public record VoteCreateCommand(
        Long playerId,
        Long matchId,
        VoteStatus status
) {

    public Vote toEntity(Player player, Match match) {
        return Vote.builder()
                .player(player)
                .match(match)
                .status(status)
                .build();
    }
}

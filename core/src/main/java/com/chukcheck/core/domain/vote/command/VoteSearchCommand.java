package com.chukcheck.core.domain.vote.command;

import com.chukcheck.core.domain.vote.model.VoteStatus;

public record VoteSearchCommand(
        Long playerId,
        Long matchId,
        VoteStatus status
) {

}

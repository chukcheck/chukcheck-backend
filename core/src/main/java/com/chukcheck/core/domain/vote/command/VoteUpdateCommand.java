package com.chukcheck.core.domain.vote.command;

import com.chukcheck.core.domain.vote.model.VoteStatus;

public record VoteUpdateCommand(
        Long id,
        VoteStatus status
) {

}

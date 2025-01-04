package com.chukcheck.api.domain.vote.dto.request;

import com.chukcheck.core.domain.vote.command.VoteCreateCommand;
import com.chukcheck.core.domain.vote.model.VoteStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class VoteCreateRequestDto {

    @NotEmpty
    private Long playerId;

    @NotEmpty
    private Long matchId;

    @NotEmpty
    private VoteStatus status;

    @Builder
    public VoteCreateRequestDto(Long playerId, Long matchId, VoteStatus status) {
        this.playerId = playerId;
        this.matchId = matchId;
        this.status = status;
    }

    public VoteCreateCommand toCommand() {
        return new VoteCreateCommand(playerId, matchId, status);
    }
}

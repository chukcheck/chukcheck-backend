package com.chukcheck.api.domain.vote.dto.request;

import com.chukcheck.core.domain.vote.command.VoteSearchCommand;
import com.chukcheck.core.domain.vote.model.VoteStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteSearchRequestDto {

    private Long playerId;
    private Long matchId;
    private VoteStatus status;

    public VoteSearchCommand toCommand() {
        return new VoteSearchCommand(playerId, matchId, status);
    }
}

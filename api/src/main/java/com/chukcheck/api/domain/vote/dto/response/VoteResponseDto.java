package com.chukcheck.api.domain.vote.dto.response;

import com.chukcheck.api.domain.match.dto.response.MatchResponseDto;
import com.chukcheck.api.domain.player.dto.response.PlayerResponseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.chukcheck.core.domain.vote.entity.Vote;
import com.chukcheck.core.domain.vote.model.VoteStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@JsonInclude(NON_NULL)
public class VoteResponseDto {

    private final Long voteId;
    private final VoteStatus status;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    private final PlayerResponseDto player;
    private final MatchResponseDto match;

    public static VoteResponseDto of(Vote vote) {
        return VoteResponseDto.builder()
                .voteId(vote.getId())
                .status(vote.getStatus())
                .createdDate(vote.getCreatedDate())
                .updatedDate(vote.getUpdatedDate())
                .player(PlayerResponseDto.of(vote.getPlayer()))
                .match(MatchResponseDto.of(vote.getMatch()))
                .build();
    }
}

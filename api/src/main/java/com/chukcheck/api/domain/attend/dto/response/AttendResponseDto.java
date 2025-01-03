package com.chukcheck.api.domain.attend.dto.response;

import com.chukcheck.core.domain.attend.entity.Attend;
import com.chukcheck.core.domain.attend.model.AttendStatus;
import com.chukcheck.api.domain.match.dto.response.MatchResponseDto;
import com.chukcheck.api.domain.player.dto.response.PlayerResponseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@JsonInclude(NON_NULL)
public class AttendResponseDto {

    private final Long attendId;
    private final AttendStatus status;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    private final PlayerResponseDto player;
    private final MatchResponseDto match;

    public static AttendResponseDto of(Attend attend) {
        return AttendResponseDto.builder()
                .attendId(attend.getId())
                .status(attend.getStatus())
                .createdDate(attend.getCreatedDate())
                .updatedDate(attend.getUpdatedDate())
                .player(PlayerResponseDto.of(attend.getPlayer()))
                .match(MatchResponseDto.of(attend.getMatch()))
                .build();
    }
}

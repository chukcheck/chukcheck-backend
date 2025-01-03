package com.chukcheck.api.domain.player.dto.response;

import com.chukcheck.api.domain.member.dto.response.MemberResponseDto;
import com.chukcheck.core.dto.response.TeamResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.chukcheck.core.common.model.BaseStatus;
import com.chukcheck.core.domain.player.entity.Player;
import com.chukcheck.core.domain.player.model.PlayerAuthority;
import com.chukcheck.core.domain.player.model.Position;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@JsonInclude(NON_NULL)
public class PlayerResponseDto {

    private final Long playerId;
    private final Integer uniformNumber;
    private final Position position;
    private final BaseStatus status;
    private final PlayerAuthority authority;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    private final MemberResponseDto member;
    private final TeamResponse team;

    public static PlayerResponseDto of(Player player) {
        return PlayerResponseDto.builder()
                .playerId(player.getId())
                .uniformNumber(player.getUniformNumber())
                .position(player.getPosition())
                .status(player.getStatus())
                .authority(player.getAuthority())
                .createdDate(player.getCreatedDate())
                .updatedDate(player.getUpdatedDate())
                .member(MemberResponseDto.of(player.getMember()))
                .team(TeamResponse.of(player.getTeam()))
                .build();
    }
}

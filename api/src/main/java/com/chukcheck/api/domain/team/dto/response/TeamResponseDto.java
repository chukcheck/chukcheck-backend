package com.chukcheck.api.domain.team.dto.response;

import com.chukcheck.api.domain.region.dto.response.RegionResponseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.chukcheck.core.common.model.BaseStatus;
import com.chukcheck.core.domain.team.entity.Team;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@SuperBuilder
@JsonInclude(NON_NULL)

public class TeamResponseDto {

    private final Long teamId;
    private final String name;
    private final BaseStatus status;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    private final RegionResponseDto region;

    public static TeamResponseDto of(Team team) {
        return TeamResponseDto.builder()
                .teamId(team.getId())
                .name(team.getName())
                .status(team.getStatus())
                .createdDate(team.getCreatedDate())
                .updatedDate(team.getUpdatedDate())
                .region(RegionResponseDto.of(team.getRegion()))
                .build();
    }
}

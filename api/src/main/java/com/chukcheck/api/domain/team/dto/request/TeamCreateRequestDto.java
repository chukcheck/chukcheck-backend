package com.chukcheck.api.domain.team.dto.request;

import com.chukcheck.core.domain.team.command.TeamCreateCommand;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class TeamCreateRequestDto {

    @NotEmpty
    private Long regionId;

    @NotEmpty
    private String name;

    @Builder
    public TeamCreateRequestDto(Long regionId, String name) {
        this.regionId = regionId;
        this.name = name;
    }

    public TeamCreateCommand toCommand() {
        return new TeamCreateCommand(regionId, name);
    }
}

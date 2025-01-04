package com.chukcheck.core.domain.team.command;

import com.chukcheck.core.domain.region.entity.Region;
import com.chukcheck.core.domain.team.entity.Team;

public record TeamCreateCommand(
        Long regionId,
        String name
) {

    public Team toEntity(Region region) {
        return Team.builder()
                .region(region)
                .name(name)
                .build();
    }
}

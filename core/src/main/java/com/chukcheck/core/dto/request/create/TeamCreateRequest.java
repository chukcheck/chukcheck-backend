package com.chukcheck.core.dto.request.create;

import com.chukcheck.core.entity.Region;
import com.chukcheck.core.entity.Team;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class TeamCreateRequest {

    @NotEmpty
    private Long regionId;

    @NotEmpty
    private String name;

    @Builder
    public TeamCreateRequest(Long regionId, String name) {
        this.regionId = regionId;
        this.name = name;
    }

    public Team toEntity(Region region) {
        return Team.builder()
                .region(region)
                .name(name)
                .build();
    }
}

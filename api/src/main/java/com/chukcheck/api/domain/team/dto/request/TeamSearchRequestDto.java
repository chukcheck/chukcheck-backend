package com.chukcheck.api.domain.team.dto.request;

import com.chukcheck.core.common.model.BaseStatus;
import com.chukcheck.core.domain.team.command.TeamSearchCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamSearchRequestDto {

    private Long regionId;
    private String name;
    private BaseStatus status;

    public TeamSearchCommand toCommand() {
        return new TeamSearchCommand(regionId, name, status);
    }
}

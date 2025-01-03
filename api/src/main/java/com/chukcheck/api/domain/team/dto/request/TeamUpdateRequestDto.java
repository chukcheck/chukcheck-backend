package com.chukcheck.api.domain.team.dto.request;

import com.chukcheck.core.common.model.BaseStatus;
import com.chukcheck.core.domain.team.command.TeamUpdateCommand;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class TeamUpdateRequestDto {

    private BaseStatus status;

    @Builder
    public TeamUpdateRequestDto(BaseStatus status) {
        this.status = status;
    }

    public TeamUpdateCommand toCommand(Long id) {
        return new TeamUpdateCommand(id, status);
    }
}

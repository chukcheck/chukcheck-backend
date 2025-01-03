package com.chukcheck.api.domain.attend.dto.request;

import com.chukcheck.core.domain.attend.command.AttendCreateCommand;
import com.chukcheck.core.domain.attend.model.AttendStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class AttendCreateRequestDto {

    @NotEmpty
    private Long playerId;

    @NotEmpty
    private Long matchId;

    @NotEmpty
    private AttendStatus status;

    @Builder
    public AttendCreateRequestDto(Long playerId, Long matchId, AttendStatus status) {
        this.playerId = playerId;
        this.matchId = matchId;
        this.status = status;
    }

    public AttendCreateCommand toCommand() {
        return new AttendCreateCommand(playerId, matchId, status);
    }
}

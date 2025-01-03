package com.chukcheck.api.domain.attend.dto.request;

import com.chukcheck.core.domain.attend.command.AttendSearchCommand;
import com.chukcheck.core.domain.attend.model.AttendStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendSearchRequestDto {

    private Long playerId;
    private Long matchId;
    private AttendStatus status;

    public AttendSearchCommand toCommand() {
        return new AttendSearchCommand(playerId, matchId, status);
    }
}

package com.chukcheck.core.domain.attend.command;

import com.chukcheck.core.domain.attend.model.AttendStatus;

public record AttendSearchCommand(
        Long playerId,
        Long matchId,
        AttendStatus status
) {

}

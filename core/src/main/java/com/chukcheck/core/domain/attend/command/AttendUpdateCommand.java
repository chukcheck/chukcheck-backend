package com.chukcheck.core.domain.attend.command;

import com.chukcheck.core.domain.attend.model.AttendStatus;

public record AttendUpdateCommand(
        Long id,
        AttendStatus status
) {

}

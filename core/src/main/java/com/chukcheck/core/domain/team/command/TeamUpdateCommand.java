package com.chukcheck.core.domain.team.command;

import com.chukcheck.core.common.model.BaseStatus;

public record TeamUpdateCommand(
        Long id,
        BaseStatus status
) {

}

package com.chukcheck.core.domain.team.command;

import com.chukcheck.core.common.model.BaseStatus;

public record TeamSearchCommand(
        Long regionId,
        String name,
        BaseStatus status
) {

}

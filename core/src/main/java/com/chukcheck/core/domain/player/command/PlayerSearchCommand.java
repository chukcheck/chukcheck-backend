package com.chukcheck.core.domain.player.command;

import com.chukcheck.core.common.model.BaseStatus;
import com.chukcheck.core.domain.player.model.PlayerAuthority;
import com.chukcheck.core.domain.player.model.Position;

public record PlayerSearchCommand(
        Long memberId,
        Long teamId,
        Position position,
        BaseStatus status,
        PlayerAuthority authority
) {

}

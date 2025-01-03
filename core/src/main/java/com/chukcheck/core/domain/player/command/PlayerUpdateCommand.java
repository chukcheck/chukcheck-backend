package com.chukcheck.core.domain.player.command;

import com.chukcheck.core.common.model.BaseStatus;
import com.chukcheck.core.domain.player.model.PlayerAuthority;
import com.chukcheck.core.domain.player.model.Position;
import lombok.Builder;
import lombok.Getter;

public record PlayerUpdateCommand(
        Long id,
        Integer uniformNumber,
        Position position,
        BaseStatus status,
        PlayerAuthority authority
) {


}

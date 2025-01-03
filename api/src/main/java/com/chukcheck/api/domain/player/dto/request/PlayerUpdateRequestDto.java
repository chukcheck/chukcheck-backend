package com.chukcheck.api.domain.player.dto.request;

import com.chukcheck.core.common.model.BaseStatus;
import com.chukcheck.core.domain.player.command.PlayerUpdateCommand;
import com.chukcheck.core.domain.player.model.PlayerAuthority;
import com.chukcheck.core.domain.player.model.Position;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class PlayerUpdateRequestDto {

    private Integer uniformNumber;
    private Position position;
    private BaseStatus status;
    private PlayerAuthority authority;

    @Builder
    public PlayerUpdateRequestDto(Integer uniformNumber, Position position, BaseStatus status, PlayerAuthority authority) {
        this.uniformNumber = uniformNumber;
        this.position = position;
        this.status = status;
        this.authority = authority;
    }

    public PlayerUpdateCommand toCommand(Long id) {
        return new PlayerUpdateCommand(id, uniformNumber, position, status, authority);
    }
}

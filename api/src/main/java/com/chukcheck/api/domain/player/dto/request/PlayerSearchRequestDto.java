package com.chukcheck.api.domain.player.dto.request;

import com.chukcheck.core.common.model.BaseStatus;
import com.chukcheck.core.domain.player.command.PlayerSearchCommand;
import com.chukcheck.core.domain.player.model.PlayerAuthority;
import com.chukcheck.core.domain.player.model.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerSearchRequestDto {

    private Long memberId;
    private Long teamId;
    private Position position;
    private BaseStatus status;
    private PlayerAuthority authority;

    public PlayerSearchCommand toCommand() {
        return new PlayerSearchCommand(memberId, teamId, position, status, authority);
    }
}

package com.chukcheck.api.domain.player.dto.request;

import com.chukcheck.core.domain.player.command.PlayerCreateCommand;
import com.chukcheck.core.domain.player.model.PlayerAuthority;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class PlayerCreateRequestDto {

    @NotEmpty
    private Long memberId;

    @NotEmpty
    private Long teamId;

    @NotEmpty
    private PlayerAuthority authority;

    @Builder
    public PlayerCreateRequestDto(Long memberId, Long teamId, PlayerAuthority authority) {
        this.memberId = memberId;
        this.teamId = teamId;
        this.authority = authority;
    }

    public PlayerCreateCommand toCommand() {
        return new PlayerCreateCommand(memberId, teamId, authority);
    }
}

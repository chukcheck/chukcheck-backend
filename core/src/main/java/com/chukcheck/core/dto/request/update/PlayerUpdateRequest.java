package com.chukcheck.core.dto.request.update;

import com.chukcheck.core.entity.BaseStatus;
import com.chukcheck.core.entity.PlayerAuthority;
import com.chukcheck.core.entity.Position;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class PlayerUpdateRequest {

    private Integer uniformNumber;
    private Position position;
    private BaseStatus status;
    private PlayerAuthority authority;

    @Builder
    public PlayerUpdateRequest(Integer uniformNumber, Position position, BaseStatus status, PlayerAuthority authority) {
        this.uniformNumber = uniformNumber;
        this.position = position;
        this.status = status;
        this.authority = authority;
    }
}

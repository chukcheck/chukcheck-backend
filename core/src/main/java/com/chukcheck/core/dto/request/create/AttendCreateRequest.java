package com.chukcheck.core.dto.request.create;

import com.chukcheck.core.entity.Attend;
import com.chukcheck.core.entity.AttendStatus;
import com.chukcheck.core.entity.Match;
import com.chukcheck.core.entity.Player;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class AttendCreateRequest {

    @NotEmpty
    private Long playerId;

    @NotEmpty
    private Long matchId;

    @NotEmpty
    private AttendStatus status;

    @Builder
    public AttendCreateRequest(Long playerId, Long matchId, AttendStatus status) {
        this.playerId = playerId;
        this.matchId = matchId;
        this.status = status;
    }

    public Attend toEntity(Player player, Match match) {
        return Attend.builder()
                .player(player)
                .match(match)
                .status(status)
                .build();
    }
}

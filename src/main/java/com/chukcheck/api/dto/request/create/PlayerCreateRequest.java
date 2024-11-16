package com.chukcheck.api.dto.request.create;

import com.chukcheck.api.entity.Member;
import com.chukcheck.api.entity.Player;
import com.chukcheck.api.entity.PlayerAuthority;
import com.chukcheck.api.entity.Team;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class PlayerCreateRequest {

    @NotEmpty
    private Long memberId;

    @NotEmpty
    private Long teamId;

    @NotEmpty
    private PlayerAuthority authority;

    @Builder
    public PlayerCreateRequest(Long memberId, Long teamId, PlayerAuthority authority) {
        this.memberId = memberId;
        this.teamId = teamId;
        this.authority = authority;
    }

    public Player toEntity(Member member, Team team) {
        return Player.builder()
                .member(member)
                .team(team)
                .authority(authority)
                .build();
    }
}

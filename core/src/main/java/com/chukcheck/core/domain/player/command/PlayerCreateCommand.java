package com.chukcheck.core.domain.player.command;

import com.chukcheck.core.domain.member.entity.Member;
import com.chukcheck.core.domain.player.entity.Player;
import com.chukcheck.core.domain.player.model.PlayerAuthority;
import com.chukcheck.core.domain.team.entity.Team;
import lombok.Getter;

public record PlayerCreateCommand(
        Long memberId,
        Long teamId,
        PlayerAuthority authority
) {

    public Player toEntity(Member member, Team team) {
        return Player.builder()
                .member(member)
                .team(team)
                .authority(authority)
                .build();
    }
}

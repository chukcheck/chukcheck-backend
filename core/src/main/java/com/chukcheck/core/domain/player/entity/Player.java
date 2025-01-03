package com.chukcheck.core.domain.player.entity;

import com.chukcheck.core.common.model.BaseStatus;
import com.chukcheck.core.common.model.BaseTime;
import com.chukcheck.core.domain.member.entity.Member;
import com.chukcheck.core.domain.player.model.PlayerAuthority;
import com.chukcheck.core.domain.player.model.Position;
import com.chukcheck.core.domain.team.entity.Team;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.chukcheck.core.common.model.BaseStatus.APPROVE;
import static com.chukcheck.core.common.model.BaseStatus.WAIT;
import static com.chukcheck.core.domain.player.model.PlayerAuthority.LEADER;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Player extends BaseTime {

    @Id
    @GeneratedValue
    @Column(name = "player_id")
    private Long id;

    private int uniformNumber;

    @Enumerated(STRING)
    private Position position;

    @Enumerated(STRING)
    private BaseStatus status;

    @Enumerated(STRING)
    private PlayerAuthority authority;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder
    public Player(Member member, Team team, PlayerAuthority authority) {
        this.authority = authority;
        this.status = selectedStatusByAuthority();

        setMember(member);
        setTeam(team);
    }

    private BaseStatus selectedStatusByAuthority() {
        return LEADER.equals(authority) ? APPROVE : WAIT;
    }

    private void setMember(Member member) {
        this.member = member;
        this.member.getPlayers().add(this);
    }

    private void setTeam(Team team) {
        this.team = team;
        this.team.getPlayers().add(this);
    }

    public void updateUniformNumber(Integer uniformNumber) {
        if (uniformNumber != null) {
            this.uniformNumber = uniformNumber;
        }
    }

    public void updatePosition(Position position) {
        if (position != null) {
            this.position = position;
        }
    }

    public void updateStatus(BaseStatus status) {
        if (status != null) {
            this.status = status;
        }
    }

    public void updateAuthority(PlayerAuthority authority) {
        if (authority != null) {
            this.authority = authority;
        }
    }
}

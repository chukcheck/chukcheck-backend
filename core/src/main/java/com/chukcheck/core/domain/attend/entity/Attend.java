package com.chukcheck.core.domain.attend.entity;

import com.chukcheck.core.domain.attend.model.AttendStatus;
import com.chukcheck.core.common.model.BaseTime;
import com.chukcheck.core.domain.match.entity.Match;
import com.chukcheck.core.domain.player.entity.Player;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Attend extends BaseTime {

    @Id
    @GeneratedValue
    @Column(name = "attend_id")
    private Long id;

    @Enumerated(STRING)
    private AttendStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

    @Builder
    public Attend(AttendStatus status, Player player, Match match) {
        this.status = status;
        this.player = player;

        setMatch(match);
    }

    public static Attend ofAbsent(Player player, Match match) {
        return Attend.builder()
                .status(AttendStatus.ABSENT)
                .player(player)
                .match(match)
                .build();
    }

    public void updateStatus(AttendStatus status) {
        if (status != null) {
            this.status = status;
        }
    }

    private void setMatch(Match match) {
        this.match = match;
        this.match.getAttends().add(this);
    }
}

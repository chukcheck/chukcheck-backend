package com.chukcheck.core.domain.vote.entity;

import com.chukcheck.core.domain.match.entity.Match;
import com.chukcheck.core.domain.player.entity.Player;
import com.chukcheck.core.common.model.BaseTime;
import com.chukcheck.core.domain.vote.model.VoteStatus;
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
public class Vote extends BaseTime {

    @Id
    @GeneratedValue
    @Column(name = "vote_id")
    private Long id;

    @Enumerated(STRING)
    private VoteStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

    @Builder
    public Vote(VoteStatus status, Player player, Match match) {
        this.status = status;
        this.player = player;

        setMatch(match);
    }

    public static Vote ofWait(Player player, Match match) {
        return new Vote(VoteStatus.WAIT, player, match);
    }

    public void updateStatus(VoteStatus status) {
        if (status != null) {
            this.status = status;
        }
    }

    private void setMatch(Match match) {
        this.match = match;
        this.match.getVotes().add(this);
    }
}

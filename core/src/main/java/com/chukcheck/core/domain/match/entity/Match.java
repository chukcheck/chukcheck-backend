package com.chukcheck.core.domain.match.entity;

import com.chukcheck.core.common.model.BaseTime;
import com.chukcheck.core.domain.attend.entity.Attend;
import com.chukcheck.core.domain.match.model.MatchAttendDate;
import com.chukcheck.core.domain.match.model.MatchDate;
import com.chukcheck.core.domain.match.model.MatchStatus;
import com.chukcheck.core.domain.match.model.MatchVoteDate;
import com.chukcheck.core.domain.stadium.entity.Stadium;
import com.chukcheck.core.domain.team.entity.Team;
import com.chukcheck.core.domain.vote.entity.Vote;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.chukcheck.core.domain.match.model.MatchStatus.CREATE;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Match extends BaseTime {

    @Id
    @GeneratedValue
    @Column(name = "match_id")
    private Long id;

    private String otherTeamName;

    private boolean home;

    private String notice;

    @Enumerated(STRING)
    private MatchStatus status;

    @Embedded
    private MatchDate matchDate;

    @Embedded
    private MatchVoteDate matchVoteDate;

    @Embedded
    private MatchAttendDate matchAttendDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    @OneToMany(mappedBy = "match")
    private final List<Vote> votes = new ArrayList<>();

    @OneToMany(mappedBy = "match")
    private final List<Attend> attends = new ArrayList<>();

    @Builder
    public Match(String otherTeamName,
                 boolean home,
                 String notice,
                 MatchDate matchDate,
                 MatchVoteDate matchVoteDate,
                 MatchAttendDate matchAttendDate,
                 Team team,
                 Stadium stadium) {
        this.otherTeamName = otherTeamName;
        this.home = home;
        this.notice = notice;
        this.matchDate = matchDate;
        this.matchVoteDate = matchVoteDate;
        this.matchAttendDate = matchAttendDate;
        this.stadium = stadium;
        this.status = CREATE;

        setTeam(team);
    }

    private void setTeam(Team team) {
        this.team = team;
        this.team.getMatches().add(this);
    }

    public void updateOtherTeamName(String otherTeamName) {
        if (otherTeamName != null) {
            this.otherTeamName = otherTeamName;
        }
    }

    public void updateHome(Boolean home) {
        if (home != null) {
            this.home = home;
        }
    }

    public void updateNotice(String notice) {
        if (notice != null) {
            this.notice = notice;
        }
    }

    public void updateStatus(MatchStatus status) {
        if (status != null) {
            this.status = status;
        }
    }

    public void updateStadium(Stadium stadium) {
        if (stadium != null) {
            this.stadium = stadium;
        }
    }

    public void updateMatchStartDate(LocalDateTime matchStartDate) {
        this.matchDate.updateStartDate(matchStartDate);
    }

    public void updateMatchEndDate(LocalDateTime matchEndDate) {
        this.matchDate.updateEndDate(matchEndDate);
    }

    public void updateVoteStartDate(LocalDateTime voteStartDate) {
        this.matchVoteDate.updateStartDate(voteStartDate);
    }

    public void updateVoteEndDate(LocalDateTime voteEndDate) {
        this.matchVoteDate.updateEndDate(voteEndDate);
    }

    public void updateAttendDeadlineDate(LocalDateTime attendDeadlineDate) {
        this.matchAttendDate.updateDeadlineDate(attendDeadlineDate);
    }

    public Long getTeamId() {
        return team.getId();
    }
}

package com.chukcheck.core.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.chukcheck.core.entity.MatchStatus.CREATE;
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
    private VoteDate voteDate;

    @Embedded
    private AttendDate attendDate;

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

    public Match(String otherTeamName, MatchDate matchDate, Team team) {
        this.otherTeamName = otherTeamName;
        this.matchDate = matchDate;

        setTeam(team);
    }

    @Builder
    public Match(String otherTeamName,
                 boolean home,
                 String notice,
                 MatchDate matchDate,
                 VoteDate voteDate,
                 AttendDate attendDate,
                 Team team,
                 Stadium stadium) {
        this.otherTeamName = otherTeamName;
        this.home = home;
        this.notice = notice;
        this.matchDate = matchDate;
        this.voteDate = voteDate;
        this.attendDate = attendDate;
        this.stadium = stadium;
        this.status = CREATE;

        setTeam(team);
    }

    private void setTeam(Team team) {
        this.team = team;
        this.team.getMatches().add(this);
    }

    public void setOtherTeamName(String otherTeamName) {
        this.otherTeamName = otherTeamName;
    }

    public void setHome(boolean home) {
        this.home = home;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public void setMatchDate(MatchDate matchDate) {
        this.matchDate = matchDate;
    }

    public void setVoteDate(VoteDate voteDate) {
        this.voteDate = voteDate;
    }

    public void setAttendDate(AttendDate attendDate) {
        this.attendDate = attendDate;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }
}

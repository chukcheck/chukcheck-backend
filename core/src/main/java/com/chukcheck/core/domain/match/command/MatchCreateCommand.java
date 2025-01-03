package com.chukcheck.core.domain.match.command;

import com.chukcheck.core.domain.match.entity.Match;
import com.chukcheck.core.domain.match.model.MatchAttendDate;
import com.chukcheck.core.domain.match.model.MatchDate;
import com.chukcheck.core.domain.match.model.MatchVoteDate;
import com.chukcheck.core.domain.stadium.entity.Stadium;
import com.chukcheck.core.domain.team.entity.Team;

import java.time.LocalDateTime;

public record MatchCreateCommand(
        Long teamId,
        Long stadiumId,
        String otherTeamName,
        Boolean home,
        String notice,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime voteStartDate,
        LocalDateTime voteEndDate,
        LocalDateTime attendDeadlineDate
) {

    public Match toEntity(Team team, Stadium stadium) {
        return Match.builder()
                .team(team)
                .stadium(stadium)
                .otherTeamName(otherTeamName)
                .home(home)
                .notice(notice)
                .matchDate(MatchDate.of(startDate, endDate))
                .matchVoteDate(MatchVoteDate.of(voteStartDate, voteEndDate))
                .matchAttendDate(MatchAttendDate.of(attendDeadlineDate))
                .build();
    }
}

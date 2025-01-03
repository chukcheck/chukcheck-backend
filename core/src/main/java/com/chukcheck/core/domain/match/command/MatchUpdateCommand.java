package com.chukcheck.core.domain.match.command;

import com.chukcheck.core.domain.match.model.MatchStatus;

import java.time.LocalDateTime;

public record MatchUpdateCommand(
        Long id,
        Long stadiumId,
        String otherTeamName,
        Boolean home,
        String notice,
        MatchStatus status,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime voteStartDate,
        LocalDateTime voteEndDate,
        LocalDateTime attendDeadlineDate
) {

}

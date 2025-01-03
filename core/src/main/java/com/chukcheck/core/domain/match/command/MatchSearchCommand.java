package com.chukcheck.core.domain.match.command;

import com.chukcheck.core.domain.match.model.MatchStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record MatchSearchCommand(
        Long teamId,
        Long stadiumId,
        MatchStatus status,
        LocalDate startDate,
        LocalDate endDate
) {

}

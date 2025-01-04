package com.chukcheck.api.domain.match.dto.request;

import com.chukcheck.core.domain.match.command.MatchSearchCommand;
import com.chukcheck.core.domain.match.model.MatchStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchSearchRequestDto {

    private Long teamId;
    private Long stadiumId;
    private MatchStatus status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public MatchSearchCommand toCommand() {
        return new MatchSearchCommand(teamId, stadiumId, status, startDate, endDate);
    }
}

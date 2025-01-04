package com.chukcheck.api.domain.match.dto.request;

import com.chukcheck.core.domain.match.command.MatchUpdateCommand;
import com.chukcheck.core.domain.match.model.MatchStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class MatchUpdateRequestDto {

    private Long stadiumId;
    private String otherTeamName;
    private Boolean home;
    private String notice;
    private MatchStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime voteStartDate;
    private LocalDateTime voteEndDate;
    private LocalDateTime attendDeadlineDate;

    @Builder
    public MatchUpdateRequestDto(Long stadiumId,
                                 String otherTeamName,
                                 Boolean home,
                                 String notice,
                                 MatchStatus status,
                                 LocalDateTime startDate,
                                 LocalDateTime endDate,
                                 LocalDateTime voteStartDate,
                                 LocalDateTime voteEndDate,
                                 LocalDateTime attendDeadlineDate) {
        this.stadiumId = stadiumId;
        this.otherTeamName = otherTeamName;
        this.home = home;
        this.notice = notice;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.voteStartDate = voteStartDate;
        this.voteEndDate = voteEndDate;
        this.attendDeadlineDate = attendDeadlineDate;
    }

    public MatchUpdateCommand toCommand(Long id) {
        return new MatchUpdateCommand(id,
                stadiumId,
                otherTeamName,
                home,
                notice,
                status,
                startDate,
                endDate,
                voteStartDate,
                voteEndDate,
                attendDeadlineDate);
    }
}

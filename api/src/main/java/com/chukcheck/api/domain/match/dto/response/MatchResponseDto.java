package com.chukcheck.api.domain.match.dto.response;

import com.chukcheck.core.domain.match.entity.Match;
import com.chukcheck.core.domain.match.model.MatchAttendDate;
import com.chukcheck.core.domain.match.model.MatchDate;
import com.chukcheck.core.domain.match.model.MatchStatus;
import com.chukcheck.core.domain.match.model.MatchVoteDate;
import com.chukcheck.api.domain.stadium.dto.response.StadiumResponseDto;
import com.chukcheck.api.domain.team.dto.response.TeamResponseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@JsonInclude(NON_NULL)
public class MatchResponseDto {

    private final Long matchId;
    private final String otherTeamName;
    private final Boolean home;
    private final String notice;
    private final MatchStatus status;
    private final MatchDate matchDate;
    private final MatchVoteDate matchVoteDate;
    private final MatchAttendDate matchAttendDate;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    private final TeamResponseDto team;
    private final StadiumResponseDto stadium;

    public static MatchResponseDto of(Match match) {
        return MatchResponseDto.builder()
                .matchId(match.getId())
                .otherTeamName(match.getOtherTeamName())
                .home(match.isHome())
                .notice(match.getNotice())
                .status(match.getStatus())
                .matchDate(match.getMatchDate())
                .matchVoteDate(match.getMatchVoteDate())
                .matchAttendDate(match.getMatchAttendDate())
                .createdDate(match.getCreatedDate())
                .updatedDate(match.getUpdatedDate())
                .team(TeamResponseDto.of(match.getTeam()))
                .stadium(StadiumResponseDto.of(match.getStadium()))
                .build();
    }
}

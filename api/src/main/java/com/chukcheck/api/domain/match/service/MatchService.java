package com.chukcheck.api.domain.match.service;

import com.chukcheck.api.domain.match.dto.response.MatchResponseDto;
import com.chukcheck.core.domain.match.application.MatchReader;
import com.chukcheck.core.domain.match.application.MatchWriter;
import com.chukcheck.core.domain.match.command.MatchCreateCommand;
import com.chukcheck.core.domain.match.command.MatchSearchCommand;
import com.chukcheck.core.domain.match.command.MatchUpdateCommand;
import com.chukcheck.core.domain.match.entity.Match;
import com.chukcheck.core.domain.stadium.application.StadiumReader;
import com.chukcheck.core.domain.stadium.entity.Stadium;
import com.chukcheck.core.domain.team.application.TeamReader;
import com.chukcheck.core.domain.team.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MatchService implements MatchUseCase {

    private final MatchReader matchReader;
    private final MatchWriter matchWriter;
    private final TeamReader teamReader;
    private final StadiumReader stadiumReader;

    @Override
    public MatchResponseDto create(MatchCreateCommand command) {
        Team team = teamReader.findById(command.teamId());
        Stadium stadium = stadiumReader.findById(command.stadiumId());

        Match match = matchWriter.save(command.toEntity(team, stadium));

        return MatchResponseDto.of(match);
    }

    @Override
    public MatchResponseDto update(MatchUpdateCommand command) {
        Match match = matchReader.findById(command.id());
        Stadium stadium = findStadiumNullable(command.stadiumId());

        match.updateStadium(stadium);
        match.updateOtherTeamName(command.otherTeamName());
        match.updateHome(command.home());
        match.updateNotice(command.notice());
        match.updateStatus(command.status());
        match.updateMatchStartDate(command.startDate());
        match.updateMatchEndDate(command.endDate());
        match.updateVoteStartDate(command.voteStartDate());
        match.updateVoteEndDate(command.voteEndDate());
        match.updateAttendDeadlineDate(command.attendDeadlineDate());

        return MatchResponseDto.of(match);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatchResponseDto> readAll(MatchSearchCommand command) {
        return matchReader.findQueryBySearch(command).stream()
                .map(MatchResponseDto::of)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MatchResponseDto read(Long id) {
        Match match = matchReader.findQueryById(id);
        return MatchResponseDto.of(match);
    }

    private Stadium findStadiumNullable(Long id) {
        if (id == null) {
            return null;
        }

        return stadiumReader.findById(id);
    }
}

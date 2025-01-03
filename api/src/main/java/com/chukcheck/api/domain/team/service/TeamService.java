package com.chukcheck.api.domain.team.service;

import com.chukcheck.api.common.exception.ApiError;
import com.chukcheck.api.common.exception.ApiException;
import com.chukcheck.api.domain.team.dto.response.TeamResponseDto;
import com.chukcheck.core.domain.region.application.RegionReader;
import com.chukcheck.core.domain.region.entity.Region;
import com.chukcheck.core.domain.team.application.TeamReader;
import com.chukcheck.core.domain.team.application.TeamWriter;
import com.chukcheck.core.domain.team.command.TeamCreateCommand;
import com.chukcheck.core.domain.team.command.TeamSearchCommand;
import com.chukcheck.core.domain.team.command.TeamUpdateCommand;
import com.chukcheck.core.domain.team.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamService implements TeamUseCase {

    private final TeamReader teamReader;
    private final TeamWriter teamWriter;
    private final RegionReader regionReader;

    @Override
    public TeamResponseDto create(TeamCreateCommand command) {
        validateAlreadyTeamName(command.name());

        Region region = regionReader.findById(command.regionId());
        Team team = teamWriter.save(command.toEntity(region));

        return TeamResponseDto.of(team);
    }

    @Override
    public TeamResponseDto update(TeamUpdateCommand command) {
        Team team = teamReader.findById(command.id());

        team.updateStatus(command.status());

        return TeamResponseDto.of(team);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamResponseDto> readAll(TeamSearchCommand command) {
        return teamReader.findQueryBySearch(command).stream()
                .map(TeamResponseDto::of)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TeamResponseDto read(Long id) {
        Team team = teamReader.findQueryById(id);
        return TeamResponseDto.of(team);
    }

    private void validateAlreadyTeamName(String name) {
        Team team = teamReader.findNullableByName(name);

        if (team != null) {
            throw new ApiException(ApiError.ALREADY_TEAM);
        }
    }
}

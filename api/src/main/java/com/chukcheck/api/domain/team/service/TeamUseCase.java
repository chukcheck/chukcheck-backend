package com.chukcheck.api.domain.team.service;

import com.chukcheck.api.domain.team.dto.response.TeamResponseDto;
import com.chukcheck.core.domain.team.command.TeamCreateCommand;
import com.chukcheck.core.domain.team.command.TeamSearchCommand;
import com.chukcheck.core.domain.team.command.TeamUpdateCommand;

import java.util.List;

public interface TeamUseCase {

    TeamResponseDto create(TeamCreateCommand command);

    TeamResponseDto update(TeamUpdateCommand command);

    List<TeamResponseDto> readAll(TeamSearchCommand command);

    TeamResponseDto read(Long id);

}

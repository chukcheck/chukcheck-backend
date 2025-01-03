package com.chukcheck.api.domain.match.service;

import com.chukcheck.api.domain.match.dto.response.MatchResponseDto;
import com.chukcheck.core.domain.match.command.MatchCreateCommand;
import com.chukcheck.core.domain.match.command.MatchSearchCommand;
import com.chukcheck.core.domain.match.command.MatchUpdateCommand;

import java.util.List;

public interface MatchUseCase {

    MatchResponseDto create(MatchCreateCommand command);

    MatchResponseDto update(MatchUpdateCommand command);

    List<MatchResponseDto> readAll(MatchSearchCommand command);

    MatchResponseDto read(Long id);

}

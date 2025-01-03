package com.chukcheck.api.domain.player.service;

import com.chukcheck.api.domain.player.dto.response.PlayerResponseDto;
import com.chukcheck.core.domain.player.command.PlayerCreateCommand;
import com.chukcheck.core.domain.player.command.PlayerSearchCommand;
import com.chukcheck.core.domain.player.command.PlayerUpdateCommand;

import java.util.List;

public interface PlayerUseCase {

    PlayerResponseDto create(PlayerCreateCommand command);

    PlayerResponseDto update(PlayerUpdateCommand command);

    List<PlayerResponseDto> readAll(PlayerSearchCommand command);

    PlayerResponseDto read(Long id);

}

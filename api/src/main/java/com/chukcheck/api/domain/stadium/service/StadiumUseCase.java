package com.chukcheck.api.domain.stadium.service;

import com.chukcheck.api.domain.stadium.dto.response.StadiumResponseDto;
import com.chukcheck.core.domain.stadium.command.StadiumCreateCommand;

import java.util.List;

public interface StadiumUseCase {

    StadiumResponseDto create(StadiumCreateCommand command);

    List<StadiumResponseDto> readAll();

    StadiumResponseDto read(Long id);
}

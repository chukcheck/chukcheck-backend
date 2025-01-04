package com.chukcheck.api.domain.sns.service;

import com.chukcheck.api.domain.sns.dto.response.SnsResponseDto;
import com.chukcheck.core.domain.sns.command.SnsCreateCommand;
import com.chukcheck.core.domain.sns.command.SnsSearchCommand;

import java.util.List;

public interface SnsUseCase {

    SnsResponseDto create(SnsCreateCommand command);

    List<SnsResponseDto> readAll(SnsSearchCommand search);

    SnsResponseDto read(Long id);

}

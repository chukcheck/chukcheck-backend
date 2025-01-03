package com.chukcheck.api.domain.vote.service;

import com.chukcheck.api.domain.vote.dto.response.VoteResponseDto;
import com.chukcheck.core.domain.vote.command.VoteCreateCommand;
import com.chukcheck.core.domain.vote.command.VoteSearchCommand;
import com.chukcheck.core.domain.vote.command.VoteUpdateCommand;

import java.util.List;

public interface VoteUseCase {

    VoteResponseDto create(VoteCreateCommand command);

    VoteResponseDto update(VoteUpdateCommand command);

    List<VoteResponseDto> readAll(VoteSearchCommand command);

    VoteResponseDto read(Long id);

}

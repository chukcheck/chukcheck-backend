package com.chukcheck.api.domain.attend.service;

import com.chukcheck.api.domain.attend.dto.response.AttendResponseDto;
import com.chukcheck.core.domain.attend.command.AttendCreateCommand;
import com.chukcheck.core.domain.attend.command.AttendSearchCommand;
import com.chukcheck.core.domain.attend.command.AttendUpdateCommand;

import java.util.List;

public interface AttendUseCase {

    AttendResponseDto create(AttendCreateCommand command);

    AttendResponseDto update(AttendUpdateCommand command);

    List<AttendResponseDto> readAll(AttendSearchCommand command);

    AttendResponseDto read(Long id);

}

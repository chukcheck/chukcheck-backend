package com.chukcheck.api.domain.member.service;

import com.chukcheck.api.domain.member.dto.response.MemberResponseDto;
import com.chukcheck.core.domain.member.command.MemberCreateCommand;
import com.chukcheck.core.domain.member.command.MemberSearchCommand;

import java.util.List;

public interface MemberUseCase {

    MemberResponseDto create(MemberCreateCommand command);

    List<MemberResponseDto> readAll(MemberSearchCommand command);

    MemberResponseDto read(Long id);

}

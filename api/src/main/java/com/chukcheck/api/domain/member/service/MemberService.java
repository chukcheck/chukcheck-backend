package com.chukcheck.api.domain.member.service;

import com.chukcheck.api.common.exception.ApiError;
import com.chukcheck.api.common.exception.ApiException;
import com.chukcheck.api.domain.member.dto.response.MemberResponseDto;
import com.chukcheck.core.domain.member.application.MemberReader;
import com.chukcheck.core.domain.member.application.MemberWriter;
import com.chukcheck.core.domain.member.command.MemberCreateCommand;
import com.chukcheck.core.domain.member.command.MemberSearchCommand;
import com.chukcheck.core.domain.member.entity.Member;
import com.chukcheck.core.domain.sns.application.SnsReader;
import com.chukcheck.core.domain.sns.entity.Sns;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements MemberUseCase {

    private final MemberReader memberReader;
    private final MemberWriter memberWriter;
    private final SnsReader snsReader;

    @Override
    public MemberResponseDto create(MemberCreateCommand command) {
        validateMemberName(command.name());
        validateMemberEmail(command.email());

        Sns sns = snsReader.findById(command.snsId());
        Member member = memberWriter.save(command.toEntity(sns));

        return MemberResponseDto.of(member);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberResponseDto> readAll(MemberSearchCommand command) {
        return memberReader.findQueryBySearch(command).stream()
                .map(MemberResponseDto::of)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MemberResponseDto read(Long id) {
        Member member = memberReader.findQueryById(id);
        return MemberResponseDto.of(member);
    }

    private void validateMemberName(String name) {
        Member member = memberReader.findNullableByName(name);

        if (member != null) {
            throw new ApiException(ApiError.ALREADY_MEMBER_NAME);
        }
    }

    private void validateMemberEmail(String email) {
        Member member = memberReader.findNullableByEmail(email);

        if (member != null) {
            throw new ApiException(ApiError.ALREADY_MEMBER_EMAIL);
        }
    }
}

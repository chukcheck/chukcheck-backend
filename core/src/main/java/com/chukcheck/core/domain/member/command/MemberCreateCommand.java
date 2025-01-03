package com.chukcheck.core.domain.member.command;

import com.chukcheck.core.domain.member.entity.Member;
import com.chukcheck.core.domain.sns.entity.Sns;

import java.time.LocalDate;

public record MemberCreateCommand(
        Long snsId,
        String name,
        String email,
        LocalDate birthDate
) {

    public Member toEntity(Sns sns) {
        return Member.builder()
                .sns(sns)
                .name(name)
                .email(email)
                .birthDate(birthDate)
                .build();
    }
}

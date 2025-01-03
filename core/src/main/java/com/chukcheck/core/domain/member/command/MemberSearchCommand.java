package com.chukcheck.core.domain.member.command;

public record MemberSearchCommand(
        Long snsId,
        String name,
        String email
) {

}

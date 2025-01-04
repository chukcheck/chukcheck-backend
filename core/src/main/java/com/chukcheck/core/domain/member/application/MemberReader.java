package com.chukcheck.core.domain.member.application;

import com.chukcheck.core.domain.member.command.MemberSearchCommand;
import com.chukcheck.core.domain.member.entity.Member;

import java.util.List;

public interface MemberReader {

    Member findNullableByName(String name);

    Member findNullableByEmail(String email);

    List<Member> findQueryBySearch(MemberSearchCommand command);

    Member findQueryById(Long id);

    Member findById(Long id);
}

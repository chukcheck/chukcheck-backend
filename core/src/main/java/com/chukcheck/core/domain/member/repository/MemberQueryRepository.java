package com.chukcheck.core.domain.member.repository;

import com.chukcheck.core.domain.member.command.MemberSearchCommand;
import com.chukcheck.core.domain.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberQueryRepository {

    List<Member> findQueryBySearch(MemberSearchCommand search);

    Optional<Member> findQueryById(Long id);

    Optional<Member> findQueryByIdAndSnsUuid(Long id, String uuid);
}

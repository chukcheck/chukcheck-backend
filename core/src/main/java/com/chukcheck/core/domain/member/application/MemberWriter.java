package com.chukcheck.core.domain.member.application;

import com.chukcheck.core.domain.member.entity.Member;

public interface MemberWriter {

    Member save(Member entity);
}

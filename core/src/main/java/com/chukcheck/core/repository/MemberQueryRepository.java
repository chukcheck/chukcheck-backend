package com.chukcheck.core.repository;

import com.chukcheck.core.dto.search.MemberSearch;
import com.chukcheck.core.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberQueryRepository {

    List<Member> findQueryBySearch(MemberSearch search);
    Optional<Member> findQueryById(Long id);
    Optional<Member> findQueryByIdAndSnsUuid(Long id, String uuid);
}

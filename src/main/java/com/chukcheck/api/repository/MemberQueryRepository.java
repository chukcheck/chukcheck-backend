package com.chukcheck.api.repository;

import com.chukcheck.api.dto.search.MemberSearch;
import com.chukcheck.api.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberQueryRepository {

    List<Member> findQueryBySearch(MemberSearch search);
    Optional<Member> findQueryById(Long id);
    Optional<Member> findQueryByIdAndSnsUuid(Long id, String uuid);
}

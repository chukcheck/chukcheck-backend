package com.chukcheck.core.repository;

import com.chukcheck.core.dto.search.VoteSearch;
import com.chukcheck.core.entity.Vote;

import java.util.List;
import java.util.Optional;

public interface VoteQueryRepository {

    List<Vote> findQueryBySearch(VoteSearch search);
    Optional<Vote> findQueryById(Long id);
}

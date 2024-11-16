package com.chukcheck.api.repository;

import com.chukcheck.api.dto.search.VoteSearch;
import com.chukcheck.api.entity.Vote;

import java.util.List;
import java.util.Optional;

public interface VoteQueryRepository {

    List<Vote> findQueryBySearch(VoteSearch search);
    Optional<Vote> findQueryById(Long id);
}

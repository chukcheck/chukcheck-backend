package com.chukcheck.core.repository;

import com.chukcheck.core.dto.search.MatchSearch;
import com.chukcheck.core.entity.Match;
import com.chukcheck.core.entity.MatchStatus;

import java.util.List;
import java.util.Optional;

public interface MatchQueryRepository {

    List<Match> findQueryBySearch(MatchSearch search);
    List<Match> findQueryVoteStartByStatus(MatchStatus status);
    List<Match> findQueryVoteEndByStatus(MatchStatus status);
    List<Match> findQueryMatchStartByStatus(MatchStatus status);
    List<Match> findQueryMatchEndByStatus(MatchStatus matchStatus);

    Optional<Match> findQueryById(Long id);
}

package com.chukcheck.api.repository;

import com.chukcheck.api.dto.search.MatchSearch;
import com.chukcheck.api.entity.Match;
import com.chukcheck.api.entity.MatchStatus;

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

package com.chukcheck.core.domain.match.repository;

import com.chukcheck.core.domain.match.command.MatchSearchCommand;
import com.chukcheck.core.domain.match.entity.Match;
import com.chukcheck.core.domain.match.model.MatchStatus;

import java.util.List;
import java.util.Optional;

public interface MatchQueryRepository {

    List<Match> findQueryBySearch(MatchSearchCommand search);

    List<Match> findQueryVoteStartByStatus(MatchStatus status);

    List<Match> findQueryVoteEndByStatus(MatchStatus status);

    List<Match> findQueryMatchStartByStatus(MatchStatus status);

    List<Match> findQueryMatchEndByStatus(MatchStatus matchStatus);

    Optional<Match> findQueryById(Long id);
}

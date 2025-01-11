package com.chukcheck.core.domain.match.repository;

import com.chukcheck.core.domain.match.command.MatchSearchCommand;
import com.chukcheck.core.domain.match.command.MatchSearchDateCommand;
import com.chukcheck.core.domain.match.entity.Match;

import java.util.List;
import java.util.Optional;

public interface MatchQueryRepository {

    List<Match> findQueryBySearch(MatchSearchCommand search);

    List<Match> findQueryBySearchDate(MatchSearchDateCommand command);

    Optional<Match> findQueryById(Long id);
}

package com.chukcheck.core.domain.match.application;

import com.chukcheck.core.domain.match.command.MatchSearchCommand;
import com.chukcheck.core.domain.match.entity.Match;

import java.util.Collection;
import java.util.List;

public interface MatchReader {

    Match findById(Long id);

    Match findQueryById(Long id);

    List<Match> findQueryBySearch(MatchSearchCommand command);
}

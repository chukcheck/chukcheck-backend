package com.chukcheck.core.domain.match.application;

import com.chukcheck.core.common.exception.CoreError;
import com.chukcheck.core.common.exception.CoreException;
import com.chukcheck.core.domain.match.command.MatchSearchCommand;
import com.chukcheck.core.domain.match.command.MatchSearchDateCommand;
import com.chukcheck.core.domain.match.entity.Match;
import com.chukcheck.core.domain.match.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchPersistence implements MatchReader, MatchWriter {

    private final MatchRepository repository;

    @Override
    public Match findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CoreException(CoreError.NOT_FOUND_DOMAIN));
    }

    @Override
    public Match findQueryById(Long id) {
        return repository.findQueryById(id).orElseThrow(() -> new CoreException(CoreError.NOT_FOUND_DOMAIN));
    }

    @Override
    public List<Match> findQueryBySearch(MatchSearchCommand command) {
        return repository.findQueryBySearch(command);
    }

    @Override
    public List<Match> findQueryBySearchDate(MatchSearchDateCommand command) {
        return repository.findQueryBySearchDate(command);
    }

    @Override
    public Match save(Match entity) {
        return repository.save(entity);
    }
}

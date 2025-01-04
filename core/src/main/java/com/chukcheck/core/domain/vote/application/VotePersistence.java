package com.chukcheck.core.domain.vote.application;

import com.chukcheck.core.common.exception.CoreError;
import com.chukcheck.core.common.exception.CoreException;
import com.chukcheck.core.domain.vote.command.VoteSearchCommand;
import com.chukcheck.core.domain.vote.entity.Vote;
import com.chukcheck.core.domain.vote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VotePersistence implements VoteWriter, VoteReader {

    private final VoteRepository repository;

    @Override
    public Vote findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CoreException(CoreError.NOT_FOUND_DOMAIN));
    }

    @Override
    public List<Vote> findQueryBySearch(VoteSearchCommand command) {
        return repository.findQueryBySearch(command);
    }

    @Override
    public Vote findQueryById(Long id) {
        return repository.findQueryById(id).orElseThrow(() -> new CoreException(CoreError.NOT_FOUND_DOMAIN));
    }

    @Override
    public Vote save(Vote entity) {
        return repository.save(entity);
    }
}

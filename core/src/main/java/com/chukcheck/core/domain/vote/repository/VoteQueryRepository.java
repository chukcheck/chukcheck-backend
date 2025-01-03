package com.chukcheck.core.domain.vote.repository;

import com.chukcheck.core.domain.vote.command.VoteSearchCommand;
import com.chukcheck.core.domain.vote.entity.Vote;

import java.util.List;
import java.util.Optional;

public interface VoteQueryRepository {

    List<Vote> findQueryBySearch(VoteSearchCommand command);

    Optional<Vote> findQueryById(Long id);
}

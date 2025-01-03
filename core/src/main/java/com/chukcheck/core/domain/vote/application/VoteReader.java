package com.chukcheck.core.domain.vote.application;

import com.chukcheck.core.domain.vote.command.VoteSearchCommand;
import com.chukcheck.core.domain.vote.entity.Vote;

import java.util.List;

public interface VoteReader {

    Vote findById(Long id);

    List<Vote> findQueryBySearch(VoteSearchCommand command);

    Vote findQueryById(Long id);
}

package com.chukcheck.core.domain.vote.application;

import com.chukcheck.core.domain.vote.entity.Vote;

public interface VoteWriter {

    Vote save(Vote entity);
}

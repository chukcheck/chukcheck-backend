package com.chukcheck.core.domain.match.application;

import com.chukcheck.core.domain.match.entity.Match;

public interface MatchWriter {

    Match save(Match entity);
}

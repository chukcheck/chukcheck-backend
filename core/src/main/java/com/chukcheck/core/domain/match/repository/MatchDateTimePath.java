package com.chukcheck.core.domain.match.repository;

import com.querydsl.core.types.dsl.DateTimePath;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import static com.chukcheck.core.domain.match.entity.QMatch.match;

@RequiredArgsConstructor
public enum MatchDateTimePath {

    VOTE_START_DATE("투표시작일시", match.matchVoteDate.startDate),
    VOTE_END_DATE("투표종료일시", match.matchVoteDate.startDate),
    MATCH_END_DATE("매치종료일시", match.matchVoteDate.startDate),
    ;

    private final String description;
    private final DateTimePath<LocalDateTime> dateTimePath;

    public DateTimePath<LocalDateTime> getPath() {
        return dateTimePath;
    }
}

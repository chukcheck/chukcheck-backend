package com.chukcheck.core.domain.match.command;

import com.chukcheck.core.domain.match.model.MatchStatus;
import com.chukcheck.core.domain.match.repository.MatchDateTimePath;

public record MatchSearchDateCommand(
        MatchStatus status,
        MatchDateTimePath dateTimePath
) {

    public static MatchSearchDateCommand attendable() {
        return new MatchSearchDateCommand(MatchStatus.VOTE, MatchDateTimePath.VOTE_END_DATE);
    }

    public static MatchSearchDateCommand finishable() {
        return new MatchSearchDateCommand(MatchStatus.MATCH, MatchDateTimePath.MATCH_END_DATE);
    }

    public static MatchSearchDateCommand startable() {
        return new MatchSearchDateCommand(MatchStatus.ATTEND, MatchDateTimePath.MATCH_END_DATE);
    }

    public static MatchSearchDateCommand votable() {
        return new MatchSearchDateCommand(MatchStatus.CREATE, MatchDateTimePath.VOTE_START_DATE);
    }
}

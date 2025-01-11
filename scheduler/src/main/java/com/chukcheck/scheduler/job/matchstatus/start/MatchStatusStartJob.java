package com.chukcheck.scheduler.job.matchstatus.start;

import com.chukcheck.core.domain.match.application.MatchReader;
import com.chukcheck.core.domain.match.command.MatchSearchDateCommand;
import com.chukcheck.core.domain.match.entity.Match;
import com.chukcheck.core.domain.match.model.MatchStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchStatusStartJob {

    private final MatchReader matchReader;

    public void execute() {
        List<Match> startableMatches = matchReader.findQueryBySearchDate(MatchSearchDateCommand.startable());
        startableMatches.forEach(this::updateMatchStatus);
    }

    private void updateMatchStatus(Match match) {
        match.updateStatus(MatchStatus.MATCH);
    }
}

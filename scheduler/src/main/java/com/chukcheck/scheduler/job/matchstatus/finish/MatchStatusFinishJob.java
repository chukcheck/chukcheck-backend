package com.chukcheck.scheduler.job.matchstatus.finish;

import com.chukcheck.core.domain.match.application.MatchReader;
import com.chukcheck.core.domain.match.command.MatchSearchDateCommand;
import com.chukcheck.core.domain.match.entity.Match;
import com.chukcheck.core.domain.match.model.MatchStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchStatusFinishJob {

    private final MatchReader matchReader;

    public void execute() {
        List<Match> finishableMatches = matchReader.findQueryBySearchDate(MatchSearchDateCommand.finishable());
        finishableMatches.forEach(this::updateFinishStatus);
    }

    private void updateFinishStatus(Match match) {
        match.updateStatus(MatchStatus.FINISH);
    }
}

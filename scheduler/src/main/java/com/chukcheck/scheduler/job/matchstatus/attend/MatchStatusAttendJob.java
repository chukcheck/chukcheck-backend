package com.chukcheck.scheduler.job.matchstatus.attend;

import com.chukcheck.core.domain.attend.application.AttendWriter;
import com.chukcheck.core.domain.attend.entity.Attend;
import com.chukcheck.core.domain.match.application.MatchReader;
import com.chukcheck.core.domain.match.command.MatchSearchDateCommand;
import com.chukcheck.core.domain.vote.application.VoteReader;
import com.chukcheck.core.domain.vote.entity.Vote;
import com.chukcheck.core.domain.match.entity.Match;
import com.chukcheck.core.domain.match.model.MatchStatus;
import com.chukcheck.core.domain.vote.command.VoteSearchCommand;
import com.chukcheck.core.domain.match.vo.MatchPlayer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchStatusAttendJob {

    private final AttendWriter attendWriter;
    private final MatchReader matchReader;
    private final VoteReader voteReader;

    public void execute() {
        List<Match> attendableMatches = matchReader.findQueryBySearchDate(MatchSearchDateCommand.attendable());

        updateAttendMatchStatus(attendableMatches);
        createAttendPlayers(attendableMatches);
    }

    private void updateAttendMatchStatus(List<Match> attendableMatches) {
        attendableMatches.forEach(match -> match.updateStatus(MatchStatus.ATTEND));
    }

    private void createAttendPlayers(List<Match> attendableMatches) {
        List<MatchPlayer> attendMatchPlayers = findAttendMatchPlayers(attendableMatches);
        attendMatchPlayers.forEach(this::createAttend);
    }

    private List<MatchPlayer> findAttendMatchPlayers(List<Match> attendableMatches) {
        return attendableMatches.stream()
                .map(this::findTrueVotesByMatch)
                .flatMap(List::stream)
                .map(this::extractMatchPlayer)
                .toList();
    }

    private MatchPlayer extractMatchPlayer(Vote vote) {
        return MatchPlayer.of(vote.getMatch(), vote.getPlayer());
    }

    private List<Vote> findTrueVotesByMatch(Match match) {
        return voteReader.findQueryBySearch(VoteSearchCommand.ofTrueByMatchId(match.getId()));
    }

    private void createAttend(MatchPlayer matchPlayer) {
        attendWriter.save(Attend.ofAbsent(matchPlayer.player(), matchPlayer.match()));
    }
}

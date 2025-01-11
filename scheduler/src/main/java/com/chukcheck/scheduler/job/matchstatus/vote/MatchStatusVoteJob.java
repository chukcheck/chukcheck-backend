package com.chukcheck.scheduler.job.matchstatus.vote;

import com.chukcheck.core.domain.match.application.MatchReader;
import com.chukcheck.core.domain.match.command.MatchSearchDateCommand;
import com.chukcheck.core.domain.match.entity.Match;
import com.chukcheck.core.domain.match.model.MatchStatus;
import com.chukcheck.core.domain.player.application.PlayerReader;
import com.chukcheck.core.domain.player.command.PlayerSearchCommand;
import com.chukcheck.core.domain.player.entity.Player;
import com.chukcheck.core.domain.vote.application.VoteWriter;
import com.chukcheck.core.domain.vote.entity.Vote;
import com.chukcheck.core.domain.match.vo.MatchPlayer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchStatusVoteJob {

    private final PlayerReader playerReader;
    private final MatchReader matchReader;
    private final VoteWriter voteWriter;

    public void execute() {
        List<Match> votableMatches = matchReader.findQueryBySearchDate(MatchSearchDateCommand.votable());

        updateVoteMatchStatus(votableMatches);
        createVotePlayers(votableMatches);
    }

    private void updateVoteMatchStatus(List<Match> votableMatches) {
        votableMatches.forEach(match -> match.updateStatus(MatchStatus.VOTE));
    }

    private void createVotePlayers(List<Match> votableMatches) {
        List<MatchPlayer> voteMatchPlayers = findVoteMatchPlayers(votableMatches);
        voteMatchPlayers.forEach(this::createVote);
    }

    private List<MatchPlayer> findVoteMatchPlayers(List<Match> votableMatches) {
        return votableMatches.stream()
                .map(this::findTeamPlayers)
                .flatMap(List::stream)
                .toList();
    }


    private List<MatchPlayer> findTeamPlayers(Match match) {
        List<Player> players = playerReader.findQueryBySearch(PlayerSearchCommand.ofApproveByTeamId(match.getTeamId()));
        return players.stream()
                .map(player -> MatchPlayer.of(match, player))
                .toList();
    }

    private void createVote(MatchPlayer matchPlayer) {
        voteWriter.save(Vote.ofWait(matchPlayer.player(), matchPlayer.match()));
    }
}

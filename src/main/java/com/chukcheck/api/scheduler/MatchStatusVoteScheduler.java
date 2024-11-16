package com.chukcheck.api.scheduler;

import com.chukcheck.api.dto.request.create.VoteCreateRequest;
import com.chukcheck.api.dto.request.update.MatchUpdateRequest;
import com.chukcheck.api.dto.search.PlayerSearch;
import com.chukcheck.api.entity.Match;
import com.chukcheck.api.service.MatchService;
import com.chukcheck.api.service.PlayerService;
import com.chukcheck.api.service.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static com.chukcheck.api.entity.BaseStatus.APPROVE;
import static com.chukcheck.api.entity.MatchStatus.VOTE;
import static com.chukcheck.api.entity.VoteStatus.WAIT;

@Slf4j
@Component
@Profile("!local")
@RequiredArgsConstructor
public class MatchStatusVoteScheduler {

    private final PlayerService playerService;
    private final MatchService matchService;
    private final VoteService voteService;

    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void doSchedule() {
        matchService.findForVoteCreate()
                .stream()
                .map(this::voteStatus)
                .flatMap(this::getPlayers)
                .forEach(this::voteCreate);
        log.info("{}-Scheduler {}", getClass().getSimpleName(), LocalDateTime.now());
    }

    private Match voteStatus(Match match) {
        return matchService.update(match.getId(), MatchUpdateRequest.builder()
                        .status(VOTE)
                        .build());
    }

    private void voteCreate(VoteCreateRequest request) {
        voteService.create(request);
    }

    private Stream<VoteCreateRequest> getPlayers(Match match) {
        return playerService.readSearch(PlayerSearch.builder()
                        .teamId(match.getTeam().getId())
                        .status(APPROVE)
                        .build())
                .stream().map(player -> VoteCreateRequest.builder()
                        .playerId(player.getId())
                        .matchId(match.getId())
                        .status(WAIT)
                        .build());
    }
}

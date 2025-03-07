package com.chukcheck.scheduler.scheduler;

import com.chukcheck.core.dto.request.create.AttendCreateRequest;
import com.chukcheck.core.dto.request.update.MatchUpdateRequest;
import com.chukcheck.core.dto.search.VoteSearch;
import com.chukcheck.core.entity.Match;
import com.chukcheck.core.entity.MatchStatus;
import com.chukcheck.scheduler.service.AttendService;
import com.chukcheck.scheduler.service.MatchService;
import com.chukcheck.scheduler.service.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static com.chukcheck.core.entity.AttendStatus.ABSENT;
import static com.chukcheck.core.entity.VoteStatus.TRUE;

@Slf4j
@Component
@Profile("!local")
@RequiredArgsConstructor
public class MatchStatusAttendScheduler {

    private final MatchService matchService;
    private final VoteService voteService;
    private final AttendService attendService;

    @Scheduled(fixedDelay = 60000, initialDelay = 20000)
    public void doSchedule() {
        matchService.findForAttendCreate()
                .stream()
                .map(this::attendStatus)
                .flatMap(this::getPlayers)
                .forEach(this::attendCreate);
        log.info("{}-Scheduler {}", getClass().getSimpleName(), LocalDateTime.now());
    }

    private Match attendStatus(Match match) {
        return matchService.update(match.getId(), MatchUpdateRequest.builder()
                .status(MatchStatus.ATTEND)
                .build());
    }

    private void attendCreate(AttendCreateRequest request) {
        attendService.create(request);
    }

    private Stream<AttendCreateRequest> getPlayers(Match match) {
        return voteService.readAll(VoteSearch.builder()
                        .matchId(match.getId())
                        .status(TRUE)
                        .build())
                .stream().map(vote -> AttendCreateRequest.builder()
                        .playerId(vote.getPlayer().getId())
                        .matchId(match.getId())
                        .status(ABSENT)
                        .build());
    }
}

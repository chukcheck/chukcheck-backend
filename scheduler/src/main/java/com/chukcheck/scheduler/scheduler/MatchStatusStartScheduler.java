package com.chukcheck.scheduler.scheduler;

import com.chukcheck.core.dto.request.update.MatchUpdateRequest;
import com.chukcheck.core.entity.Match;
import com.chukcheck.core.entity.MatchStatus;
import com.chukcheck.scheduler.service.MatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@Profile("!local")
@RequiredArgsConstructor
public class MatchStatusStartScheduler {

    private final MatchService matchService;

    @Scheduled(fixedDelay = 60000, initialDelay = 40000)
    public void doSchedule() {
        matchService.findForMatchStart()
                .forEach(this::startStatus);
        log.info("{}-Scheduler {}", getClass().getSimpleName(), LocalDateTime.now());
    }

    private void startStatus(Match match) {
        matchService.update(match.getId(), MatchUpdateRequest.builder()
                        .status(MatchStatus.MATCH)
                        .build());
    }

}

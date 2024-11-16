package com.chukcheck.api.scheduler;

import com.chukcheck.api.dto.request.update.MatchUpdateRequest;
import com.chukcheck.api.entity.Match;
import com.chukcheck.api.entity.MatchStatus;
import com.chukcheck.api.service.MatchService;
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

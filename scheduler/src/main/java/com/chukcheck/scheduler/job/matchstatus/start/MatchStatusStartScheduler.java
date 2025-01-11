package com.chukcheck.scheduler.job.matchstatus.start;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("!local")
@RequiredArgsConstructor
public class MatchStatusStartScheduler {

    private final MatchStatusStartJob matchStatusStartJob;

    @Scheduled(fixedDelay = 60000, initialDelay = 40000)
    public void execute() {
        matchStatusStartJob.execute();
    }
}

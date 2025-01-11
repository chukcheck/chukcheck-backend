package com.chukcheck.scheduler.job.matchstatus.finish;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("!local")
@RequiredArgsConstructor
public class MatchStatusFinishScheduler {

    private final MatchStatusFinishJob matchStatusFinishJob;

    @Scheduled(fixedDelay = 60000, initialDelay = 60000)
    public void execute() {
        matchStatusFinishJob.execute();
    }
}

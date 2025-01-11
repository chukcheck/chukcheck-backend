package com.chukcheck.scheduler.job.matchstatus.attend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("!local")
@RequiredArgsConstructor
public class MatchStatusAttendScheduler {

    private final MatchStatusAttendJob matchStatusAttendJob;

    @Scheduled(fixedDelay = 60000, initialDelay = 20000)
    public void execute() {
        matchStatusAttendJob.execute();
    }
}

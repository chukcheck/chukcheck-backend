package com.chukcheck.scheduler.job.matchstatus.vote;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("!local")
@RequiredArgsConstructor
public class MatchStatusVoteScheduler {

    private final MatchStatusVoteJob matchStatusVoteJob;

    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void execute() {
        matchStatusVoteJob.execute();
    }
}

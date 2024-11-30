package com.chukcheck.scheduler.service;

import com.chukcheck.core.dto.request.create.AttendCreateRequest;
import com.chukcheck.core.entity.Attend;
import com.chukcheck.core.entity.Match;
import com.chukcheck.core.entity.Player;
import com.chukcheck.core.repository.AttendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AttendService {

    private final AttendRepository attendRepository;
    private final PlayerService playerService;
    private final MatchService matchService;

    public Attend create(AttendCreateRequest request) {
        return attendRepository.save(request.toEntity(findPlayer(request), findMatch(request)));
    }

    private Player findPlayer(AttendCreateRequest request) {
        return playerService.read(request.getPlayerId());
    }

    private Match findMatch(AttendCreateRequest request) {
        return matchService.read(request.getMatchId());
    }

}

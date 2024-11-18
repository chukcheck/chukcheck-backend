package com.chukcheck.api.service;

import com.chukcheck.core.dto.request.create.AttendCreateRequest;
import com.chukcheck.core.dto.request.update.AttendUpdateRequest;
import com.chukcheck.core.dto.search.AttendSearch;
import com.chukcheck.core.entity.Attend;
import com.chukcheck.core.entity.Match;
import com.chukcheck.core.entity.Player;
import com.chukcheck.core.repository.AttendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.nonNull;

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

    public Attend update(Long id, AttendUpdateRequest request) {
        Attend attend = read(id);

        status(request, attend);

        return attend;
    }

    @Transactional(readOnly = true)
    public List<Attend> readAll(AttendSearch search) {
        return attendRepository.findQueryBySearch(search);
    }

    @Transactional(readOnly = true)
    public Attend read(Long id) {
        return attendRepository.findQueryById(id).orElseThrow(() -> new IllegalArgumentException("Not exist attend"));
    }

    private Player findPlayer(AttendCreateRequest request) {
        return playerService.read(request.getPlayerId());
    }

    private Match findMatch(AttendCreateRequest request) {
        return matchService.read(request.getMatchId());
    }

    private void status(AttendUpdateRequest request, Attend attend) {
        if (nonNull(request.getStatus())) attend.setStatus(request.getStatus());
    }
}

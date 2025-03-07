package com.chukcheck.api.service;

import com.chukcheck.core.dto.request.create.VoteCreateRequest;
import com.chukcheck.core.dto.request.update.VoteUpdateRequest;
import com.chukcheck.core.dto.search.VoteSearch;
import com.chukcheck.core.entity.Match;
import com.chukcheck.core.entity.Player;
import com.chukcheck.core.entity.Vote;
import com.chukcheck.core.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final PlayerService playerService;
    private final MatchService matchService;

    public Vote create(VoteCreateRequest request) {
        return voteRepository.save(request.toEntity(findPlayer(request), findMatch(request)));
    }

    public Vote update(Long id, VoteUpdateRequest request) {
        Vote vote = read(id);

        status(request, vote);

        return vote;
    }

    @Transactional(readOnly = true)
    public List<Vote> readAll(VoteSearch search) {
        return voteRepository.findQueryBySearch(search);
    }

    @Transactional(readOnly = true)
    public Vote read(Long id) {
        return voteRepository.findQueryById(id).orElseThrow(() -> new IllegalArgumentException("Not exist vote"));
    }

    private Player findPlayer(VoteCreateRequest request) {
        return playerService.read(request.getPlayerId());
    }

    private Match findMatch(VoteCreateRequest request) {
        return matchService.read(request.getMatchId());
    }

    private void status(VoteUpdateRequest request, Vote vote) {
        if (nonNull(request.getStatus())) vote.setStatus(request.getStatus());
    }
}

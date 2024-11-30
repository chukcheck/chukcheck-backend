package com.chukcheck.scheduler.service;

import com.chukcheck.core.dto.request.create.VoteCreateRequest;
import com.chukcheck.core.dto.search.VoteSearch;
import com.chukcheck.core.entity.Match;
import com.chukcheck.core.entity.Player;
import com.chukcheck.core.entity.Vote;
import com.chukcheck.core.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public List<Vote> readAll(VoteSearch search) {
        return voteRepository.findQueryBySearch(search);
    }

    private Player findPlayer(VoteCreateRequest request) {
        return playerService.read(request.getPlayerId());
    }

    private Match findMatch(VoteCreateRequest request) {
        return matchService.read(request.getMatchId());
    }
}

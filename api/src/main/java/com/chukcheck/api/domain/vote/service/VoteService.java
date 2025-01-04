package com.chukcheck.api.domain.vote.service;

import com.chukcheck.api.domain.vote.dto.response.VoteResponseDto;
import com.chukcheck.core.domain.match.application.MatchReader;
import com.chukcheck.core.domain.match.entity.Match;
import com.chukcheck.core.domain.player.application.PlayerReader;
import com.chukcheck.core.domain.player.entity.Player;
import com.chukcheck.core.domain.vote.application.VoteReader;
import com.chukcheck.core.domain.vote.application.VoteWriter;
import com.chukcheck.core.domain.vote.command.VoteCreateCommand;
import com.chukcheck.core.domain.vote.command.VoteSearchCommand;
import com.chukcheck.core.domain.vote.command.VoteUpdateCommand;
import com.chukcheck.core.domain.vote.entity.Vote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteService implements VoteUseCase {

    private final VoteReader voteReader;
    private final VoteWriter voteWriter;
    private final PlayerReader playerReader;
    private final MatchReader matchReader;

    @Override
    public VoteResponseDto create(VoteCreateCommand command) {
        Player player = playerReader.findById(command.playerId());
        Match match = matchReader.findById(command.matchId());

        Vote vote = voteWriter.save(command.toEntity(player, match));

        return VoteResponseDto.of(vote);
    }

    @Override
    public VoteResponseDto update(VoteUpdateCommand command) {
        Vote vote = voteReader.findById(command.id());

        vote.updateStatus(command.status());

        return VoteResponseDto.of(vote);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoteResponseDto> readAll(VoteSearchCommand command) {
        return voteReader.findQueryBySearch(command).stream()
                .map(VoteResponseDto::of)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VoteResponseDto read(Long id) {
        Vote vote = voteReader.findQueryById(id);
        return VoteResponseDto.of(vote);
    }
}

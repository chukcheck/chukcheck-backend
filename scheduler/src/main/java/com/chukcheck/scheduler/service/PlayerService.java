package com.chukcheck.scheduler.service;

import com.chukcheck.core.dto.request.create.PlayerCreateRequest;
import com.chukcheck.core.dto.search.PlayerSearch;
import com.chukcheck.core.entity.Member;
import com.chukcheck.core.entity.Player;
import com.chukcheck.core.entity.Team;
import com.chukcheck.core.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final MemberService memberService;
    private final TeamService teamService;

    public Player create(PlayerCreateRequest request) {
        validate(request);

        return playerRepository.save(request.toEntity(findMember(request), findTeam(request)));
    }

    @Transactional(readOnly = true)
    public List<Player> readSearch(PlayerSearch search) {
        return playerRepository.findQueryBySearch(search);
    }

    @Transactional(readOnly = true)
    public Player read(Long id) {
        return playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not exist player"));
    }

    private Member findMember(PlayerCreateRequest request) {
        return memberService.read(request.getMemberId());
    }

    private Team findTeam(PlayerCreateRequest request) {
        return teamService.read(request.getTeamId());
    }

    private void validate(PlayerCreateRequest request) {
        if (playerRepository.findByMemberIdAndTeamId(request.getMemberId(), request.getTeamId()).isPresent()) {
            throw new IllegalArgumentException("Already player");
        }
    }
}

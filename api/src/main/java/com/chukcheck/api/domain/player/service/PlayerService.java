package com.chukcheck.api.domain.player.service;

import com.chukcheck.api.common.exception.ApiError;
import com.chukcheck.api.common.exception.ApiException;
import com.chukcheck.api.domain.player.dto.response.PlayerResponseDto;
import com.chukcheck.core.domain.member.application.MemberReader;
import com.chukcheck.core.domain.member.entity.Member;
import com.chukcheck.core.domain.player.application.PlayerReader;
import com.chukcheck.core.domain.player.application.PlayerWriter;
import com.chukcheck.core.domain.player.command.PlayerCreateCommand;
import com.chukcheck.core.domain.player.command.PlayerSearchCommand;
import com.chukcheck.core.domain.player.command.PlayerUpdateCommand;
import com.chukcheck.core.domain.player.entity.Player;
import com.chukcheck.core.domain.team.application.TeamReader;
import com.chukcheck.core.domain.team.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerService implements PlayerUseCase {

    private final PlayerReader playerReader;
    private final PlayerWriter playerWriter;
    private final MemberReader memberReader;
    private final TeamReader teamReader;

    @Override
    public PlayerResponseDto create(PlayerCreateCommand command) {
        validateAlreadyPlayer(command.memberId(), command.teamId());

        Member member = memberReader.findById(command.memberId());
        Team team = teamReader.findById(command.teamId());

        Player player = playerWriter.save(command.toEntity(member, team));

        return PlayerResponseDto.of(player);
    }

    @Override
    public PlayerResponseDto update(PlayerUpdateCommand command) {
        Player player = playerReader.findById(command.id());

        player.updateUniformNumber(command.uniformNumber());
        player.updatePosition(command.position());
        player.updateStatus(command.status());
        player.updateAuthority(command.authority());

        return PlayerResponseDto.of(player);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerResponseDto> readAll(PlayerSearchCommand command) {
        return playerReader.findQueryBySearch(command).stream()
                .map(PlayerResponseDto::of)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PlayerResponseDto read(Long id) {
        Player player = playerReader.findQueryById(id);
        return PlayerResponseDto.of(player);
    }

    private void validateAlreadyPlayer(Long memberId, Long teamId) {
        Player player = playerReader.findNullableByMemberIdAndTeamId(memberId, teamId);

        if (player != null) {
            throw new ApiException(ApiError.ALREADY_PLAYER);
        }
    }
}

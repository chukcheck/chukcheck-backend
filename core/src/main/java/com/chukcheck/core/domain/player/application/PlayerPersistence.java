package com.chukcheck.core.domain.player.application;

import com.chukcheck.core.common.exception.CoreError;
import com.chukcheck.core.common.exception.CoreException;
import com.chukcheck.core.domain.player.command.PlayerSearchCommand;
import com.chukcheck.core.domain.player.entity.Player;
import com.chukcheck.core.domain.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlayerPersistence implements PlayerReader, PlayerWriter {

    private final PlayerRepository repository;

    @Override
    public Player findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CoreException(CoreError.NOT_FOUND_DOMAIN));
    }

    @Override
    public Player findNullableByMemberIdAndTeamId(Long memberId, Long teamId) {
        return repository.findByMemberIdAndTeamId(memberId, teamId).orElse(null);
    }

    @Override
    public List<Player> findQueryBySearch(PlayerSearchCommand command) {
        return repository.findQueryBySearch(command);
    }

    @Override
    public Player findQueryById(Long id) {
        return repository.findQueryById(id).orElseThrow(() -> new CoreException(CoreError.NOT_FOUND_DOMAIN));
    }

    @Override
    public Player save(Player entity) {
        return repository.save(entity);
    }
}

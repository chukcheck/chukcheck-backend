package com.chukcheck.core.domain.team.application;

import com.chukcheck.core.common.exception.CoreError;
import com.chukcheck.core.common.exception.CoreException;
import com.chukcheck.core.domain.team.command.TeamSearchCommand;
import com.chukcheck.core.domain.team.entity.Team;
import com.chukcheck.core.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TeamPersistence implements TeamReader, TeamWriter {

    private final TeamRepository repository;

    @Override
    public Team findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CoreException(CoreError.NOT_FOUND_DOMAIN));
    }

    @Override
    public Team findNullableByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    @Override
    public List<Team> findQueryBySearch(TeamSearchCommand command) {
        return repository.findQueryBySearch(command);
    }

    @Override
    public Team findQueryById(Long id) {
        return repository.findQueryById(id).orElseThrow(() -> new CoreException(CoreError.NOT_FOUND_DOMAIN));
    }

    @Override
    public Team save(Team entity) {
        return repository.save(entity);
    }
}

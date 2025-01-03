package com.chukcheck.core.domain.team.repository;

import com.chukcheck.core.domain.team.command.TeamSearchCommand;
import com.chukcheck.core.domain.team.entity.Team;

import java.util.List;
import java.util.Optional;

public interface TeamQueryRepository {

    List<Team> findQueryBySearch(TeamSearchCommand command);

    Optional<Team> findQueryById(Long id);
}

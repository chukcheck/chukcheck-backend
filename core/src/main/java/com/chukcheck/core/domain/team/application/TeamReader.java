package com.chukcheck.core.domain.team.application;

import com.chukcheck.core.domain.team.command.TeamSearchCommand;
import com.chukcheck.core.domain.team.entity.Team;

import java.util.List;

public interface TeamReader {

    Team findById(Long id);

    Team findNullableByName(String name);

    List<Team> findQueryBySearch(TeamSearchCommand command);

    Team findQueryById(Long id);
}

package com.chukcheck.core.domain.team.application;

import com.chukcheck.core.domain.team.entity.Team;

public interface TeamWriter {

    Team save(Team entity);
}

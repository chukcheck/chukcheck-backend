package com.chukcheck.core.repository;

import com.chukcheck.core.dto.search.TeamSearch;
import com.chukcheck.core.entity.Team;

import java.util.List;
import java.util.Optional;

public interface TeamQueryRepository {

    List<Team> findQueryBySearch(TeamSearch search);
    Optional<Team> findQueryById(Long id);
}

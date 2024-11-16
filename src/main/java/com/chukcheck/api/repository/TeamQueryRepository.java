package com.chukcheck.api.repository;

import com.chukcheck.api.dto.search.TeamSearch;
import com.chukcheck.api.entity.Team;

import java.util.List;
import java.util.Optional;

public interface TeamQueryRepository {

    List<Team> findQueryBySearch(TeamSearch search);
    Optional<Team> findQueryById(Long id);
}

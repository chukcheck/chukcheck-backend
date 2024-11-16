package com.chukcheck.api.repository;

import com.chukcheck.api.dto.search.PlayerSearch;
import com.chukcheck.api.entity.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerQueryRepository {

    List<Player> findQueryBySearch(PlayerSearch search);
    Optional<Player> findQueryById(Long id);
}

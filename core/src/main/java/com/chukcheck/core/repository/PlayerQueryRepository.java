package com.chukcheck.core.repository;

import com.chukcheck.core.dto.search.PlayerSearch;
import com.chukcheck.core.entity.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerQueryRepository {

    List<Player> findQueryBySearch(PlayerSearch search);
    Optional<Player> findQueryById(Long id);
}

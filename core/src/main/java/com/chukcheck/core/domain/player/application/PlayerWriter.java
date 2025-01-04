package com.chukcheck.core.domain.player.application;

import com.chukcheck.core.domain.player.entity.Player;

public interface PlayerWriter {

    Player save(Player entity);
}

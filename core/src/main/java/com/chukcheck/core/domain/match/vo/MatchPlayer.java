package com.chukcheck.core.domain.match.vo;

import com.chukcheck.core.domain.match.entity.Match;
import com.chukcheck.core.domain.player.entity.Player;

public record MatchPlayer(
        Match match,
        Player player
) {

    public static MatchPlayer of(Match match, Player player) {
        return new MatchPlayer(match, player);
    }

}

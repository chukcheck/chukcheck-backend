package com.chukcheck.core.repository;

import com.chukcheck.core.dto.search.AttendSearch;
import com.chukcheck.core.entity.Attend;
import com.chukcheck.core.entity.AttendStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.chukcheck.core.entity.QAttend.attend;
import static com.chukcheck.core.entity.QMatch.match;
import static com.chukcheck.core.entity.QMember.member;
import static com.chukcheck.core.entity.QPlayer.player;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
public class AttendRepositoryImpl implements AttendQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Attend> findQueryBySearch(AttendSearch search) {
        return queryFactory
                .selectFrom(attend)
                .join(attend.player, player).fetchJoin()
                .join(player.member, member).fetchJoin()
                .join(attend.match, match).fetchJoin()
                .where(
                        playerIdEqual(search.getPlayerId()),
                        matchIdEqual(search.getMatchId()),
                        statusEqual(search.getStatus())
                ).fetch();
    }

    @Override
    public Optional<Attend> findQueryById(Long id) {
        return ofNullable(queryFactory
                .selectFrom(attend)
                .join(attend.player, player).fetchJoin()
                .join(player.member, member).fetchJoin()
                .join(attend.match, match).fetchJoin()
                .where(attend.id.eq(id))
                .fetchOne());
    }

    private BooleanExpression playerIdEqual(Long playerId) {
        return nonNull(playerId) ? attend.player.id.eq(playerId) : null;
    }

    private BooleanExpression matchIdEqual(Long matchId) {
        return nonNull(matchId) ? attend.match.id.eq(matchId) : null;
    }

    private BooleanExpression statusEqual(AttendStatus status) {
        return nonNull(status) ? attend.status.eq(status) : null;
    }
}

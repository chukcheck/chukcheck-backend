package com.chukcheck.core.domain.attend.repository;

import com.chukcheck.core.domain.attend.command.AttendSearchCommand;
import com.chukcheck.core.domain.attend.entity.Attend;
import com.chukcheck.core.domain.attend.model.AttendStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.chukcheck.core.domain.attend.entity.QAttend.attend;
import static com.chukcheck.core.domain.match.entity.QMatch.match;
import static com.chukcheck.core.domain.member.entity.QMember.member;
import static com.chukcheck.core.domain.player.entity.QPlayer.player;
import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
public class AttendRepositoryImpl implements AttendQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Attend> findQueryBySearch(AttendSearchCommand command) {
        return queryFactory
                .selectFrom(attend)
                .join(attend.player, player).fetchJoin()
                .join(player.member, member).fetchJoin()
                .join(attend.match, match).fetchJoin()
                .where(
                        playerIdEqual(command.playerId()),
                        matchIdEqual(command.matchId()),
                        statusEqual(command.status())
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
        if (playerId == null) {
            return null;
        }

        return attend.player.id.eq(playerId);
    }

    private BooleanExpression matchIdEqual(Long matchId) {
        if (matchId == null) {
            return null;
        }

        return attend.match.id.eq(matchId);
    }

    private BooleanExpression statusEqual(AttendStatus status) {
        if (status == null) {
            return null;
        }

        return attend.status.eq(status);
    }
}

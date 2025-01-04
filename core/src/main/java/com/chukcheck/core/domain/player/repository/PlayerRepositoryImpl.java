package com.chukcheck.core.domain.player.repository;

import com.chukcheck.core.common.model.BaseStatus;
import com.chukcheck.core.domain.player.command.PlayerSearchCommand;
import com.chukcheck.core.domain.player.entity.Player;
import com.chukcheck.core.domain.player.model.PlayerAuthority;
import com.chukcheck.core.domain.player.model.Position;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.chukcheck.core.domain.member.entity.QMember.member;
import static com.chukcheck.core.domain.player.entity.QPlayer.player;
import static com.chukcheck.core.domain.team.entity.QTeam.team;

@RequiredArgsConstructor
public class PlayerRepositoryImpl implements PlayerQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Player> findQueryBySearch(PlayerSearchCommand command) {
        return queryFactory
                .selectFrom(player)
                .join(player.member, member).fetchJoin()
                .join(player.team, team).fetchJoin()
                .where(
                        memberIdEqual(command.memberId()),
                        teamIdEqual(command.teamId()),
                        positionEqual(command.position()),
                        statusEqual(command.status()),
                        authorityEqual(command.authority())
                ).fetch();
    }

    @Override
    public Optional<Player> findQueryById(Long id) {
        return Optional.ofNullable(queryFactory
                .selectFrom(player)
                .join(player.member, member).fetchJoin()
                .join(player.team, team).fetchJoin()
                .where(player.id.eq(id))
                .fetchOne());
    }

    private BooleanExpression memberIdEqual(Long id) {
        if (id == null) {
            return null;
        }

        return player.member.id.eq(id);
    }

    private BooleanExpression teamIdEqual(Long id) {
        if (id == null) {
            return null;
        }

        return player.team.id.eq(id);
    }

    private BooleanExpression positionEqual(Position position) {
        if (position == null) {
            return null;
        }

        return player.position.eq(position);
    }

    private BooleanExpression statusEqual(BaseStatus status) {
        if (status == null) {
            return null;
        }

        return player.status.eq(status);
    }

    private Predicate authorityEqual(PlayerAuthority authority) {
        if (authority == null) {
            return null;
        }

        return player.authority.eq(authority);
    }
}

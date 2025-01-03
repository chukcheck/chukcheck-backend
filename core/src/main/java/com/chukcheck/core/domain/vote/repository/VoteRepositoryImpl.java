package com.chukcheck.core.domain.vote.repository;

import com.chukcheck.core.domain.vote.command.VoteSearchCommand;
import com.chukcheck.core.domain.vote.entity.Vote;
import com.chukcheck.core.domain.vote.model.VoteStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.chukcheck.core.domain.match.entity.QMatch.match;
import static com.chukcheck.core.domain.member.entity.QMember.member;
import static com.chukcheck.core.domain.player.entity.QPlayer.player;
import static com.chukcheck.core.domain.vote.entity.QVote.vote;
import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
public class VoteRepositoryImpl implements VoteQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Vote> findQueryBySearch(VoteSearchCommand command) {
        return queryFactory
                .selectFrom(vote)
                .join(vote.player, player).fetchJoin()
                .join(player.member, member).fetchJoin()
                .join(vote.match, match).fetchJoin()
                .where(
                        statusEqual(command.status()),
                        playerIdEqual(command.playerId()),
                        matchIdEqual(command.matchId())
                ).fetch();
    }

    @Override
    public Optional<Vote> findQueryById(Long id) {
        return ofNullable(queryFactory
                .selectFrom(vote)
                .join(vote.player, player).fetchJoin()
                .join(player.member, member).fetchJoin()
                .join(vote.match, match).fetchJoin()
                .where(vote.id.eq(id))
                .fetchOne());
    }

    private BooleanExpression statusEqual(VoteStatus status) {
        if (status == null) {
            return null;
        }

        return vote.status.eq(status);
    }

    private BooleanExpression playerIdEqual(Long playerId) {
        if (playerId == null) {
            return null;
        }

        return vote.player.id.eq(playerId);
    }

    private BooleanExpression matchIdEqual(Long matchId) {
        if (matchId == null) {
            return null;
        }

        return vote.match.id.eq(matchId);
    }
}

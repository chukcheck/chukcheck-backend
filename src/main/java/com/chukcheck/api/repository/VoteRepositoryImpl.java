package com.chukcheck.api.repository;

import com.chukcheck.api.dto.search.VoteSearch;
import com.chukcheck.api.entity.Vote;
import com.chukcheck.api.entity.VoteStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.chukcheck.api.entity.QMatch.match;
import static com.chukcheck.api.entity.QMember.member;
import static com.chukcheck.api.entity.QPlayer.player;
import static com.chukcheck.api.entity.QVote.vote;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
public class VoteRepositoryImpl implements VoteQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Vote> findQueryBySearch(VoteSearch search) {
        return queryFactory
                .selectFrom(vote)
                .join(vote.player, player).fetchJoin()
                .join(player.member, member).fetchJoin()
                .join(vote.match, match).fetchJoin()
                .where(
                        statusEqual(search.getStatus()),
                        playerIdEqual(search.getPlayerId()),
                        matchIdEqual(search.getMatchId())
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
        return nonNull(status) ? vote.status.eq(status) : null;
    }

    private BooleanExpression playerIdEqual(Long playerId) {
        return nonNull(playerId) ? vote.player.id.eq(playerId) : null;
    }

    private BooleanExpression matchIdEqual(Long matchId) {
        return nonNull(matchId) ? vote.match.id.eq(matchId) : null;
    }
}

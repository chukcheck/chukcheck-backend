package com.chukcheck.core.domain.match.repository;

import com.chukcheck.core.domain.match.command.MatchSearchCommand;
import com.chukcheck.core.domain.match.entity.Match;
import com.chukcheck.core.domain.match.model.MatchStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.chukcheck.core.domain.match.entity.QMatch.match;
import static com.chukcheck.core.domain.stadium.entity.QStadium.stadium;
import static com.chukcheck.core.domain.team.entity.QTeam.team;
import static java.time.LocalTime.MAX;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
public class MatchRepositoryImpl implements MatchQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Match> findQueryBySearch(MatchSearchCommand search) {
        return queryFactory
                .selectFrom(match)
                .join(match.team, team).fetchJoin()
                .join(match.stadium, stadium).fetchJoin()
                .where(
                        teamIdEqual(search.teamId()),
                        stadiumIdEqual(search.stadiumId()),
                        statusEqual(search.status()),
                        matchDateBetween(search.startDate(), search.endDate())
                ).fetch();
    }

    @Override
    public List<Match> findQueryVoteStartByStatus(MatchStatus status) {
        return queryFactory
                .selectFrom(match)
                .join(match.team, team).fetchJoin()
                .where(
                        statusEqual(status),
                        beforeVoteStartDate()
                ).fetch();
    }

    @Override
    public List<Match> findQueryVoteEndByStatus(MatchStatus status) {
        return queryFactory
                .selectFrom(match)
                .join(match.team, team).fetchJoin()
                .where(
                        statusEqual(status),
                        beforeVoteEndDate()
                ).fetch();
    }

    @Override
    public List<Match> findQueryMatchStartByStatus(MatchStatus status) {
        return queryFactory
                .selectFrom(match)
                .join(match.team, team).fetchJoin()
                .where(
                        statusEqual(status),
                        beforeMatchStartDate()
                ).fetch();
    }

    @Override
    public List<Match> findQueryMatchEndByStatus(MatchStatus status) {
        return queryFactory
                .selectFrom(match)
                .join(match.team, team).fetchJoin()
                .where(
                        statusEqual(status),
                        beforeMatchEndDate()
                ).fetch();
    }

    @Override
    public Optional<Match> findQueryById(Long id) {
        return ofNullable(queryFactory
                .selectFrom(match)
                .join(match.team, team).fetchJoin()
                .join(match.stadium, stadium).fetchJoin()
                .where(match.id.eq(id))
                .fetchOne());
    }

    private BooleanExpression beforeVoteStartDate() {
        return match.matchVoteDate.startDate.before(LocalDateTime.now());
    }

    private BooleanExpression beforeVoteEndDate() {
        return match.matchVoteDate.endDate.before(LocalDateTime.now());
    }

    private BooleanExpression beforeMatchEndDate() {
        return match.matchDate.endDate.before(LocalDateTime.now());
    }

    private BooleanExpression beforeMatchStartDate() {
        return match.matchDate.startDate.before(LocalDateTime.now());
    }

    private BooleanExpression teamIdEqual(Long id) {
        return nonNull(id) ? match.team.id.eq(id) : null;
    }

    private BooleanExpression stadiumIdEqual(Long id) {
        return nonNull(id) ? match.stadium.id.eq(id) : null;
    }

    private BooleanExpression statusEqual(MatchStatus status) {
        return nonNull(status) ? match.status.eq(status) : null;
    }

    private BooleanExpression matchDateBetween(LocalDate startDate, LocalDate endDate) {
        return nonNull(startDate) && nonNull(endDate) ? match.matchDate.startDate.goe(startDate.atStartOfDay())
                        .and(match.matchDate.endDate.loe(endDate.atTime(MAX))) : null;
    }
}

package com.chukcheck.core.domain.match.repository;

import com.chukcheck.core.domain.match.command.MatchSearchCommand;
import com.chukcheck.core.domain.match.command.MatchSearchDateCommand;
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
import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
public class MatchRepositoryImpl implements MatchQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Match> findQueryBySearch(MatchSearchCommand command) {
        return queryFactory
                .selectFrom(match)
                .join(match.team, team).fetchJoin()
                .join(match.stadium, stadium).fetchJoin()
                .where(
                        teamIdEqual(command.teamId()),
                        stadiumIdEqual(command.stadiumId()),
                        statusEqual(command.status()),
                        matchDateBetween(command.startDate(), command.endDate())
                ).fetch();
    }

    @Override
    public List<Match> findQueryBySearchDate(MatchSearchDateCommand command) {
        return queryFactory
                .selectFrom(match)
                .join(match.team, team).fetchJoin()
                .where(
                        statusEqual(command.status()),
                        currentDateBefore(command.dateTimePath())
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

    private BooleanExpression teamIdEqual(Long id) {
        if (id == null) {
            return null;
        }

        return match.team.id.eq(id);
    }

    private BooleanExpression stadiumIdEqual(Long id) {
        if (id == null) {
            return null;
        }

        return match.stadium.id.eq(id);
    }

    private BooleanExpression statusEqual(MatchStatus status) {
        if (status == null) {
            return null;
        }

        return match.status.eq(status);
    }

    private BooleanExpression matchDateBetween(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }

        return match.matchDate.startDate.goe(startDate.atStartOfDay())
                .and(match.matchDate.endDate.loe(endDate.atTime(MAX)));
    }

    private BooleanExpression currentDateBefore(MatchDateTimePath dateTimePath) {
        return dateTimePath.getPath().before(LocalDateTime.now());
    }
}

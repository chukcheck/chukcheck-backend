package com.chukcheck.core.domain.team.repository;

import com.chukcheck.core.common.model.BaseStatus;
import com.chukcheck.core.domain.team.command.TeamSearchCommand;
import com.chukcheck.core.domain.team.entity.Team;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.chukcheck.core.domain.region.entity.QRegion.region;
import static com.chukcheck.core.domain.team.entity.QTeam.team;

@RequiredArgsConstructor
public class TeamRepositoryImpl implements TeamQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Team> findQueryBySearch(TeamSearchCommand command) {
        return queryFactory
                .selectFrom(team)
                .join(team.region, region).fetchJoin()
                .where(
                        regionIdEqual(command.regionId()),
                        nameEq(command.name()),
                        statusEqual(command.status())
                ).fetch();
    }

    @Override
    public Optional<Team> findQueryById(Long id) {
        return Optional.ofNullable(queryFactory
                .selectFrom(team)
                .join(team.region, region).fetchJoin()
                .where(team.id.eq(id))
                .fetchOne());
    }

    private BooleanExpression regionIdEqual(Long id) {
        if (id == null) {
            return null;
        }

        return team.region.id.eq(id);
    }

    private BooleanExpression nameEq(String name) {
        if (name == null) {
            return null;
        }

        return team.name.eq(name);
    }

    private BooleanExpression statusEqual(BaseStatus status) {
        if (status == null) {
            return null;
        }

        return team.status.eq(status);
    }
}

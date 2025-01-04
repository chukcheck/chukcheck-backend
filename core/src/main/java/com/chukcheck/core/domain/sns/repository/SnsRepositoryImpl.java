package com.chukcheck.core.domain.sns.repository;

import com.chukcheck.core.domain.sns.command.SnsSearchCommand;
import com.chukcheck.core.domain.sns.entity.Sns;
import com.chukcheck.core.domain.sns.model.SnsType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.chukcheck.core.domain.sns.entity.QSns.sns;
import static java.util.Objects.nonNull;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class SnsRepositoryImpl implements SnsQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Sns> findQueryBySearch(SnsSearchCommand search) {
        return queryFactory
                .selectFrom(sns)
                .where(uuidEqual(search.uuid()), typeEqual(search.type()))
                .fetch();
    }

    private BooleanExpression uuidEqual(String uuid) {
        return hasText(uuid) ? sns.uuid.eq(uuid) : null;
    }

    private BooleanExpression typeEqual(SnsType type) {
        return nonNull(type) ? sns.type.eq(type) : null;
    }
}

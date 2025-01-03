package com.chukcheck.core.domain.member.repository;

import com.chukcheck.core.domain.member.command.MemberSearchCommand;
import com.chukcheck.core.domain.member.entity.Member;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.chukcheck.core.domain.member.entity.QMember.member;
import static com.chukcheck.core.domain.sns.entity.QSns.sns;
import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Member> findQueryBySearch(MemberSearchCommand search) {
        return queryFactory
                .selectFrom(member)
                .join(member.sns, sns).fetchJoin()
                .where(nameEqual(search.name()), emailEqual(search.email()), snsIdEqual(search.snsId()))
                .fetch();
    }

    @Override
    public Optional<Member> findQueryById(Long id) {
        return ofNullable(queryFactory
                .selectFrom(member)
                .join(member.sns, sns).fetchJoin()
                .where(member.id.eq(id))
                .fetchOne());
    }

    @Override
    public Optional<Member> findQueryByIdAndSnsUuid(Long id, String uuid) {
        return ofNullable(queryFactory
                .selectFrom(member)
                .join(member.sns, sns).fetchJoin()
                .where(member.id.eq(id), member.sns.uuid.eq(uuid))
                .fetchOne());
    }

    private BooleanExpression nameEqual(String name) {
        if (name == null) {
            return null;
        }

        return member.name.eq(name);
    }

    private BooleanExpression emailEqual(String email) {
        if (email == null) {
            return null;
        }

        return member.email.eq(email);
    }

    private BooleanExpression snsIdEqual(Long snsId) {
        if (snsId == null) {
            return null;
        }

        return sns.id.eq(snsId);
    }
}

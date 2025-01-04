package com.chukcheck.core.domain.member.application;

import com.chukcheck.core.common.exception.CoreError;
import com.chukcheck.core.common.exception.CoreException;
import com.chukcheck.core.domain.member.command.MemberSearchCommand;
import com.chukcheck.core.domain.member.entity.Member;
import com.chukcheck.core.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberPersistence implements MemberReader, MemberWriter {

    private final MemberRepository repository;

    @Override
    public Member findNullableByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    @Override
    public Member findNullableByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    @Override
    public List<Member> findQueryBySearch(MemberSearchCommand command) {
        return repository.findQueryBySearch(command);
    }

    @Override
    public Member findQueryById(Long id) {
        return repository.findQueryById(id).orElseThrow(() -> new CoreException(CoreError.NOT_FOUND_DOMAIN));
    }

    @Override
    public Member save(Member entity) {
        return repository.save(entity);
    }
}

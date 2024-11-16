package com.chukcheck.api.repository;

import com.chukcheck.api.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryRepository {

    Optional<Member> findByName(String name);
    Optional<Member> findByEmail(String email);
}

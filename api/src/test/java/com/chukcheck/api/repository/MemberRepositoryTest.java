package com.chukcheck.api.repository;

import com.chukcheck.core.dto.search.MemberSearch;
import com.chukcheck.core.entity.Member;
import com.chukcheck.core.entity.Sns;
import com.chukcheck.core.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.chukcheck.core.entity.SnsType.GOOGLE;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        //given
        Sns sns1 = new Sns("sns1", GOOGLE);
        em.persist(sns1);

        Member memberA = new Member("memberA", sns1);

        //when
        memberRepository.save(memberA);
        Member findMember = memberRepository.findById(memberA.getId()).get();

        //then
        assertThat(findMember).isEqualTo(memberA);
        assertThat(findMember.getId()).isEqualTo(memberA.getId());
        assertThat(findMember.getName()).isEqualTo(memberA.getName());
    }

    @Test
    void findByName() {
        //given
        Sns sns1 = new Sns("sns1", GOOGLE);
        em.persist(sns1);

        Member memberA = new Member("memberA", sns1);

        //when
        memberRepository.save(memberA);
        Member findMember = memberRepository.findByName("memberA").get();

        //then
        assertThat(findMember).isEqualTo(memberA);
        assertThat(findMember.getId()).isEqualTo(memberA.getId());
        assertThat(findMember.getName()).isEqualTo(memberA.getName());
    }

    @Test
    void findByEmail() {
        //given
        Sns sns = new Sns("sns1", GOOGLE);
        em.persist(sns);

        Member memberA = new Member("memberA", "test@test.com", LocalDate.of(2022, 1, 1) ,sns);

        //when
        memberRepository.save(memberA);
        Member findMember = memberRepository.findByEmail("test@test.com").get();

        //then
        assertThat(findMember).isEqualTo(memberA);
        assertThat(findMember.getId()).isEqualTo(memberA.getId());
        assertThat(findMember.getName()).isEqualTo(memberA.getName());
    }

    @Test
    void findQueryBySearch() {
    	//given
        Sns sns1 = new Sns("sns1", GOOGLE);

        em.persist(sns1);
        em.persist(new Member("park", sns1));

        //when
        List<Member> result = memberRepository.findQueryBySearch(MemberSearch.builder()
                        .snsId(sns1.getId())
                        .build());

        Member findMember = result.getFirst();

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(findMember.getSns().getType()).isEqualTo(sns1.getType());
        assertThat(findMember.getSns().getUuid()).isEqualTo(sns1.getUuid());
    }

    @Test
    void findQueryById() {
    	//given
        Sns sns1 = new Sns("sns1", GOOGLE);
        em.persist(sns1);

        Member member = new Member("park", sns1);
        em.persist(member);

        //when
        Member findMember = memberRepository.findQueryById(member.getId()).get();

        //then
        assertThat(findMember.getName()).isEqualTo(member.getName());
        assertThat(findMember.getSns().getType()).isEqualTo(sns1.getType());
        assertThat(findMember.getSns().getUuid()).isEqualTo(sns1.getUuid());
    }

    @Test
    void findQueryByIdAndSnsUuid() {
    	//given
        Sns sns1 = new Sns("sns1", GOOGLE);
        em.persist(sns1);

        Member member = new Member("park", sns1);
        em.persist(member);

        //when
        Member findMember = memberRepository.findQueryByIdAndSnsUuid(member.getId(), sns1.getUuid()).get();

        //then
        assertThat(findMember.getName()).isEqualTo(member.getName());
        assertThat(findMember.getSns().getType()).isEqualTo(sns1.getType());
        assertThat(findMember.getSns().getUuid()).isEqualTo(sns1.getUuid());
    }
}
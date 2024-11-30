package com.chukcheck.scheduler.service;

import com.chukcheck.core.dto.request.create.MemberCreateRequest;
import com.chukcheck.core.entity.Member;
import com.chukcheck.core.entity.Sns;
import com.chukcheck.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final SnsService snsService;

    public Member create(MemberCreateRequest request) {
        validateName(request);
        validateEmail(request);

        return memberRepository.save(request.toEntity(findSns(request)));
    }

    @Transactional(readOnly = true)
    public Member read(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not exist user"));
    }

    private Sns findSns(MemberCreateRequest memberCreateRequest) {
        return snsService.read(memberCreateRequest.getSnsId());
    }

    private void validateName(MemberCreateRequest request) {
        if (memberRepository.findByName(request.getName()).isPresent()) {
            throw new IllegalArgumentException("Already user name");
        }
    }

    private void validateEmail(MemberCreateRequest request) {
        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Already user email");
        }
    }
}

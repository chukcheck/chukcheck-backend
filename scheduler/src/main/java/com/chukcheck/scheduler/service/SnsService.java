package com.chukcheck.scheduler.service;

import com.chukcheck.core.dto.request.create.SnsCreateRequest;
import com.chukcheck.core.entity.Sns;
import com.chukcheck.core.repository.SnsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SnsService {

    private final SnsRepository snsRepository;

    public Sns create(SnsCreateRequest request) {
        validate(request);

        return snsRepository.save(request.toEntity());
    }

    @Transactional(readOnly = true)
    public Sns read(Long id) {
        return snsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not exist sns"));
    }

    private void validate(SnsCreateRequest request) {
        if (snsRepository.findByUuidAndType(request.getUuid(), request.getType()).isPresent()) {
            throw new IllegalArgumentException("Already sns");
        }
    }
}

package com.chukcheck.scheduler.service;

import com.chukcheck.core.dto.request.create.StadiumCreateRequest;
import com.chukcheck.core.entity.Stadium;
import com.chukcheck.core.repository.StadiumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StadiumService {

    private final StadiumRepository stadiumRepository;

    public Stadium create(StadiumCreateRequest request) {
        return stadiumRepository.save(request.toEntity());
    }

    @Transactional(readOnly = true)
    public Stadium read(Long id) {
        return stadiumRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found stadium"));
    }
}

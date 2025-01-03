package com.chukcheck.api.domain.stadium.service;

import com.chukcheck.api.domain.stadium.dto.response.StadiumResponseDto;
import com.chukcheck.core.domain.stadium.application.StadiumReader;
import com.chukcheck.core.domain.stadium.application.StadiumWriter;
import com.chukcheck.core.domain.stadium.command.StadiumCreateCommand;
import com.chukcheck.core.domain.stadium.entity.Stadium;
import com.chukcheck.core.domain.stadium.repository.StadiumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StadiumService implements StadiumUseCase {

    private final StadiumReader stadiumReader;
    private final StadiumWriter stadiumWriter;

    @Override
    public StadiumResponseDto create(StadiumCreateCommand command) {
        Stadium stadium = stadiumWriter.save(command.toEntity());
        return StadiumResponseDto.of(stadium);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StadiumResponseDto> readAll() {
        return stadiumReader.findAll().stream()
                .map(StadiumResponseDto::of)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StadiumResponseDto read(Long id) {
        Stadium stadium = stadiumReader.findById(id);
        return StadiumResponseDto.of(stadium);
    }
}

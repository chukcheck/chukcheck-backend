package com.chukcheck.api.domain.sns.service;

import com.chukcheck.api.common.exception.ApiException;
import com.chukcheck.api.domain.sns.dto.response.SnsResponseDto;
import com.chukcheck.core.domain.sns.application.SnsReader;
import com.chukcheck.core.domain.sns.application.SnsWriter;
import com.chukcheck.core.domain.sns.command.SnsCreateCommand;
import com.chukcheck.core.domain.sns.command.SnsSearchCommand;
import com.chukcheck.core.domain.sns.entity.Sns;
import com.chukcheck.core.domain.sns.model.SnsType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.chukcheck.api.common.exception.ApiError.ALREADY_SNS;

@Service
@Transactional
@RequiredArgsConstructor
public class SnsService implements SnsUseCase {

    private final SnsReader snsReader;
    private final SnsWriter snsWriter;

    @Override
    public SnsResponseDto create(SnsCreateCommand command) {
        validateAlreadySns(command.uuid(), command.type());

        Sns sns = snsWriter.save(command.toEntity());

        return SnsResponseDto.of(sns);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SnsResponseDto> readAll(SnsSearchCommand search) {
        return snsReader.findQueryBySearch(search).stream()
                .map(SnsResponseDto::of)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SnsResponseDto read(Long id) {
        Sns sns = snsReader.findById(id);
        return SnsResponseDto.of(sns);
    }

    private void validateAlreadySns(String uuid, SnsType type) {
        Sns sns = snsReader.findNullableByUuidAndType(uuid, type);

        if (sns != null) {
            throw new ApiException(ALREADY_SNS);
        }
    }
}

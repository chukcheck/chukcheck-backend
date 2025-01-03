package com.chukcheck.api.domain.region.service;

import com.chukcheck.api.common.exception.ApiException;
import com.chukcheck.api.domain.region.dto.response.RegionResponseDto;
import com.chukcheck.core.domain.region.application.RegionReader;
import com.chukcheck.core.domain.region.application.RegionWriter;
import com.chukcheck.core.domain.region.command.RegionCreateCommand;
import com.chukcheck.core.domain.region.entity.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.chukcheck.api.common.exception.ApiError.ALREADY_REGION;

@Service
@Transactional
@RequiredArgsConstructor
public class RegionService implements RegionUseCase {

    private final RegionReader regionReader;
    private final RegionWriter regionWriter;

    @Override
    public RegionResponseDto create(RegionCreateCommand command) {
        validateAlreadyRegion(command.country(), command.city());

        Region region = regionWriter.save(command.toEntity());

        return RegionResponseDto.of(region);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegionResponseDto> readAll() {
        return regionReader.findAll().stream()
                .map(RegionResponseDto::of)
                .toList();
    }

    private void validateAlreadyRegion(String country, String city) {
        Region region = regionReader.findNullableByCountryAndCity(country, city);

        if (region != null) {
            throw new ApiException(ALREADY_REGION);
        }
    }
}

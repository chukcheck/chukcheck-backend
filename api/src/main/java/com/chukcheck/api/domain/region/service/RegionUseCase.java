package com.chukcheck.api.domain.region.service;

import com.chukcheck.api.domain.region.dto.response.RegionResponseDto;
import com.chukcheck.core.domain.region.command.RegionCreateCommand;

import java.util.List;

public interface RegionUseCase {

    RegionResponseDto create(RegionCreateCommand command);

    List<RegionResponseDto> readAll();

}

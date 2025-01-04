package com.chukcheck.api.domain.region.controller;

import com.chukcheck.api.domain.region.dto.request.RegionCreateRequestDto;
import com.chukcheck.api.common.dto.ApiResponse;
import com.chukcheck.api.domain.region.dto.response.RegionResponseDto;
import com.chukcheck.api.domain.region.service.RegionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/region")
public class RegionController {

    private final RegionUseCase regionUseCase;

    @PostMapping
    public ApiResponse<RegionResponseDto> create(@RequestBody RegionCreateRequestDto request) {
        return ApiResponse.success(regionUseCase.create(request.toCommand()));
    }

    @GetMapping
    public ApiResponse<List<RegionResponseDto>> readAll() {
        return ApiResponse.success(regionUseCase.readAll());
    }
}
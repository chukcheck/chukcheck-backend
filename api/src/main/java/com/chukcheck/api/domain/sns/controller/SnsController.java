package com.chukcheck.api.domain.sns.controller;

import com.chukcheck.api.domain.sns.dto.request.SnsCreateRequestDto;
import com.chukcheck.api.domain.sns.dto.request.SnsSearchRequestDto;
import com.chukcheck.api.domain.sns.service.SnsUseCase;
import com.chukcheck.api.common.dto.ApiResponse;
import com.chukcheck.api.domain.sns.dto.response.SnsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sns")
public class SnsController {

    private final SnsUseCase snsUseCase;

    @PostMapping
    public ApiResponse<SnsResponseDto> create(@RequestBody SnsCreateRequestDto request) {
        return ApiResponse.success(snsUseCase.create(request.toCommand()));
    }

    @GetMapping
    public ApiResponse<List<SnsResponseDto>> readAll(SnsSearchRequestDto request) {
        return ApiResponse.success(snsUseCase.readAll(request.toCommand()));
    }

    @GetMapping("/{id}")
    public ApiResponse<SnsResponseDto> read(@PathVariable(name = "id") Long id) {
        return ApiResponse.success(snsUseCase.read(id));
    }
}

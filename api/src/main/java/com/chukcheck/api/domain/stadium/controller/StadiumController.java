package com.chukcheck.api.domain.stadium.controller;

import com.chukcheck.api.domain.stadium.dto.request.StadiumCreateRequestDto;
import com.chukcheck.api.domain.stadium.service.StadiumUseCase;
import com.chukcheck.api.common.dto.ApiResponse;
import com.chukcheck.api.domain.stadium.dto.response.StadiumResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stadium")
public class StadiumController {

    private final StadiumUseCase stadiumUseCase;

    @PostMapping
    public ApiResponse<StadiumResponseDto> create(@RequestBody StadiumCreateRequestDto request) {
        return ApiResponse.success(stadiumUseCase.create(request.toCommand()));
    }

    @GetMapping
    public ApiResponse<List<StadiumResponseDto>> readAll() {
        return ApiResponse.success(stadiumUseCase.readAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<StadiumResponseDto> read(@PathVariable(name = "id") Long id) {
        return ApiResponse.success(stadiumUseCase.read(id));
    }
}

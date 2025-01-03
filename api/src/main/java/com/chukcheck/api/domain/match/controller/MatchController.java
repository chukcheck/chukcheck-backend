package com.chukcheck.api.domain.match.controller;

import com.chukcheck.api.common.dto.ApiResponse;
import com.chukcheck.api.domain.match.dto.request.MatchCreateRequestDto;
import com.chukcheck.api.domain.match.dto.request.MatchSearchRequestDto;
import com.chukcheck.api.domain.match.dto.request.MatchUpdateRequestDto;
import com.chukcheck.api.domain.match.dto.response.MatchResponseDto;
import com.chukcheck.api.domain.match.service.MatchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/match")
public class MatchController {

    private final MatchUseCase matchUseCase;

    @PostMapping
    public ApiResponse<MatchResponseDto> create(@RequestBody MatchCreateRequestDto request) {
        return ApiResponse.success(matchUseCase.create(request.toCommand()));
    }

    @PutMapping("/{id}")
    public ApiResponse<MatchResponseDto> update(@PathVariable(name = "id") Long id,
                                                @RequestBody MatchUpdateRequestDto request) {
        return ApiResponse.success(matchUseCase.update(request.toCommand(id)));
    }

    @GetMapping
    public ApiResponse<List<MatchResponseDto>> readAll(MatchSearchRequestDto request) {
        return ApiResponse.success(matchUseCase.readAll(request.toCommand()));
    }

    @GetMapping("/{id}")
    public ApiResponse<MatchResponseDto> read(@PathVariable(name = "id") Long id) {
        return ApiResponse.success(matchUseCase.read(id));
    }
}

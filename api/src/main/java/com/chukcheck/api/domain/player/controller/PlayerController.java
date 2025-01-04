package com.chukcheck.api.domain.player.controller;

import com.chukcheck.api.domain.player.dto.request.PlayerCreateRequestDto;
import com.chukcheck.api.domain.player.dto.request.PlayerSearchRequestDto;
import com.chukcheck.api.domain.player.dto.request.PlayerUpdateRequestDto;
import com.chukcheck.api.common.dto.ApiResponse;
import com.chukcheck.api.domain.player.dto.response.PlayerResponseDto;
import com.chukcheck.api.domain.player.service.PlayerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/player")
public class PlayerController {

    private final PlayerUseCase playerUseCase;

    @PostMapping
    public ApiResponse<PlayerResponseDto> create(@RequestBody PlayerCreateRequestDto request) {
        return ApiResponse.success(playerUseCase.create(request.toCommand()));
    }

    @PutMapping("/{id}")
    public ApiResponse<PlayerResponseDto> update(@PathVariable(name = "id") Long id,
                                                 @RequestBody PlayerUpdateRequestDto request) {
        return ApiResponse.success(playerUseCase.update(request.toCommand(id)));
    }

    @GetMapping
    public ApiResponse<List<PlayerResponseDto>> readAll(PlayerSearchRequestDto request) {
        return ApiResponse.success(playerUseCase.readAll(request.toCommand()));
    }

    @GetMapping("/{id}")
    public ApiResponse<PlayerResponseDto> read(@PathVariable(name = "id") Long id) {
        return ApiResponse.success(playerUseCase.read(id));
    }
}

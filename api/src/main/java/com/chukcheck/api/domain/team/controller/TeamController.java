package com.chukcheck.api.domain.team.controller;

import com.chukcheck.api.domain.team.dto.request.TeamCreateRequestDto;
import com.chukcheck.api.domain.team.dto.request.TeamSearchRequestDto;
import com.chukcheck.api.domain.team.dto.request.TeamUpdateRequestDto;
import com.chukcheck.api.domain.team.service.TeamUseCase;
import com.chukcheck.api.common.dto.ApiResponse;
import com.chukcheck.api.domain.team.dto.response.TeamResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/team")
public class TeamController {

    private final TeamUseCase teamUseCase;

    @PostMapping
    public ApiResponse<TeamResponseDto> create(@RequestBody TeamCreateRequestDto request) {
        return ApiResponse.success(teamUseCase.create(request.toCommand()));
    }

    @PutMapping("/{id}")
    public ApiResponse<TeamResponseDto> update(@PathVariable(name = "id") Long id,
                                               @RequestBody TeamUpdateRequestDto request) {
        return ApiResponse.success(teamUseCase.update(request.toCommand(id)));
    }

    @GetMapping
    public ApiResponse<List<TeamResponseDto>> readAll(TeamSearchRequestDto request) {
        return ApiResponse.success(teamUseCase.readAll(request.toCommand()));
    }

    @GetMapping("/{id}")
    public ApiResponse<TeamResponseDto> read(@PathVariable(name = "id") Long id) {
        return ApiResponse.success(teamUseCase.read(id));
    }
}

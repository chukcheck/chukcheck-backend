package com.chukcheck.api.domain.vote.controller;

import com.chukcheck.api.domain.vote.dto.request.VoteCreateRequestDto;
import com.chukcheck.api.domain.vote.dto.request.VoteSearchRequestDto;
import com.chukcheck.api.domain.vote.dto.request.VoteUpdateRequestDto;
import com.chukcheck.api.domain.vote.service.VoteUseCase;
import com.chukcheck.api.common.dto.ApiResponse;
import com.chukcheck.api.domain.vote.dto.response.VoteResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vote")
public class VoteController {

    private final VoteUseCase voteUseCase;

    @PostMapping
    public ApiResponse<VoteResponseDto> create(@RequestBody VoteCreateRequestDto request) {
        return ApiResponse.success(voteUseCase.create(request.toCommand()));
    }

    @PutMapping("/{id}")
    public ApiResponse<VoteResponseDto> update(@PathVariable(name = "id") Long id,
                                               @RequestBody VoteUpdateRequestDto request) {
        return ApiResponse.success(voteUseCase.update(request.toCommand(id)));
    }

    @GetMapping
    public ApiResponse<List<VoteResponseDto>> readAll(VoteSearchRequestDto request) {
        return ApiResponse.success(voteUseCase.readAll(request.toCommand()));
    }

    @GetMapping("/{id}")
    public ApiResponse<VoteResponseDto> read(@PathVariable(name = "id") Long id) {
        return ApiResponse.success(voteUseCase.read(id));
    }
}

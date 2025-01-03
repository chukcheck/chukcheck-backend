package com.chukcheck.api.domain.member.controller;

import com.chukcheck.api.common.dto.ApiResponse;
import com.chukcheck.api.domain.member.dto.request.MemberCreateRequestDto;
import com.chukcheck.api.domain.member.dto.request.MemberSearchRequestDto;
import com.chukcheck.api.domain.member.dto.response.MemberResponseDto;
import com.chukcheck.api.domain.member.service.MemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberUseCase memberUseCase;

    @PostMapping
    public ApiResponse<MemberResponseDto> create(@RequestBody MemberCreateRequestDto request) {
        return ApiResponse.success(memberUseCase.create(request.toCommand()));
    }

    @GetMapping
    public ApiResponse<List<MemberResponseDto>> readAll(MemberSearchRequestDto request) {
        return ApiResponse.success(memberUseCase.readAll(request.toCommand()));
    }

    @GetMapping("/{id}")
    public ApiResponse<MemberResponseDto> read(@PathVariable(name = "id") Long id) {
        return ApiResponse.success(memberUseCase.read(id));
    }
}

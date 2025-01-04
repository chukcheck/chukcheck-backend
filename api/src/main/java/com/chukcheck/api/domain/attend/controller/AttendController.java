package com.chukcheck.api.domain.attend.controller;

import com.chukcheck.api.domain.attend.dto.request.AttendCreateRequestDto;
import com.chukcheck.api.domain.attend.dto.request.AttendSearchRequestDto;
import com.chukcheck.api.domain.attend.dto.request.AttendUpdateRequestDto;
import com.chukcheck.api.domain.attend.dto.response.AttendResponseDto;
import com.chukcheck.api.common.dto.ApiResponse;
import com.chukcheck.api.domain.attend.service.AttendUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attend")
public class AttendController {

    private final AttendUseCase attendUseCase;

    @PostMapping
    public ApiResponse<AttendResponseDto> create(@RequestBody AttendCreateRequestDto request) {
        return ApiResponse.success(attendUseCase.create(request.toCommand()));
    }

    @PutMapping("/{id}")
    public ApiResponse<AttendResponseDto> update(@PathVariable(name = "id") Long id,
                                                 @RequestBody AttendUpdateRequestDto request) {
        return ApiResponse.success(attendUseCase.update(request.toCommand(id)));
    }

    @GetMapping
    public ApiResponse<List<AttendResponseDto>> readAll(AttendSearchRequestDto request) {
        return ApiResponse.success(attendUseCase.readAll(request.toCommand()));
    }

    @GetMapping("/{id}")
    public ApiResponse<AttendResponseDto> read(@PathVariable(name = "id") Long id) {
        return ApiResponse.success(attendUseCase.read(id));
    }
}

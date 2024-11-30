package com.chukcheck.api.controller;

import com.chukcheck.core.dto.request.create.RegionCreateRequest;
import com.chukcheck.core.dto.response.BaseResponse;
import com.chukcheck.core.dto.response.RegionResponse;
import com.chukcheck.api.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/region")
public class RegionController {

    private final RegionService regionService;

    @PostMapping
    public BaseResponse<RegionResponse> create(@RequestBody RegionCreateRequest request) {
        return new BaseResponse<>(RegionResponse.of(regionService.create(request)));
    }

    @GetMapping
    public BaseResponse<List<RegionResponse>> readAll() {
        return new BaseResponse<>(regionService.readAll().stream()
                .map(RegionResponse::of)
                .collect(toList()));
    }
}
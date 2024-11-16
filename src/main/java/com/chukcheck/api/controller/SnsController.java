package com.chukcheck.api.controller;

import com.chukcheck.api.dto.request.create.SnsCreateRequest;
import com.chukcheck.api.dto.response.BaseResponse;
import com.chukcheck.api.dto.response.SnsResponse;
import com.chukcheck.api.dto.search.SnsSearch;
import com.chukcheck.api.service.SnsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.chukcheck.api.dto.response.SnsResponse.of;
import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sns")
public class SnsController {

    private final SnsService snsService;

    @PostMapping
    public BaseResponse<SnsResponse> create(@RequestBody @Valid SnsCreateRequest request) {
        return new BaseResponse<>(of(snsService.create(request)));
    }

    @GetMapping
    public BaseResponse<List<SnsResponse>> readAll(SnsSearch search) {
        return new BaseResponse<>(snsService.readSearch(search).stream()
                .map(SnsResponse::of)
                .collect(toList()));
    }

    @GetMapping("/{id}")
    public BaseResponse<SnsResponse> read(@PathVariable(name = "id") Long id) {
        return new BaseResponse<>(of(snsService.read(id)));
    }
}

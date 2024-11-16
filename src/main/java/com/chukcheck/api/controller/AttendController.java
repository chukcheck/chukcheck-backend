package com.chukcheck.api.controller;

import com.chukcheck.api.dto.request.create.AttendCreateRequest;
import com.chukcheck.api.dto.request.update.AttendUpdateRequest;
import com.chukcheck.api.dto.response.AttendResponse;
import com.chukcheck.api.dto.response.BaseResponse;
import com.chukcheck.api.dto.search.AttendSearch;
import com.chukcheck.api.service.AttendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.chukcheck.api.dto.response.AttendResponse.of;
import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attend")
public class AttendController {

    private final AttendService attendService;

    @PostMapping
    public BaseResponse<AttendResponse> create(@RequestBody AttendCreateRequest request) {
        return new BaseResponse<>(of(attendService.create(request)));
    }

    @PutMapping("/{id}")
    public BaseResponse<AttendResponse> update(@PathVariable(name = "id") Long id,
                                               @RequestBody AttendUpdateRequest request) {
        return new BaseResponse<>(of(attendService.update(id, request)));
    }

    @GetMapping
    public BaseResponse<List<AttendResponse>> readAll(AttendSearch search) {
        return new BaseResponse<>(attendService.readAll(search).stream()
                .map(AttendResponse::of)
                .collect(toList()));
    }

    @GetMapping("/{id}")
    public BaseResponse<AttendResponse> read(@PathVariable(name = "id") Long id) {
        return new BaseResponse<>(of(attendService.read(id)));
    }
}

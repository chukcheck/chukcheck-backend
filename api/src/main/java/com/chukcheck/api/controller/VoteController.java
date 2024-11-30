package com.chukcheck.api.controller;

import com.chukcheck.core.dto.request.create.VoteCreateRequest;
import com.chukcheck.core.dto.request.update.VoteUpdateRequest;
import com.chukcheck.core.dto.response.BaseResponse;
import com.chukcheck.core.dto.response.VoteResponse;
import com.chukcheck.core.dto.search.VoteSearch;
import com.chukcheck.api.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.chukcheck.core.dto.response.VoteResponse.of;
import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vote")
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    public BaseResponse<VoteResponse> create(@RequestBody VoteCreateRequest request) {
        return new BaseResponse<>(of(voteService.create(request)));
    }

    @PutMapping("/{id}")
    public BaseResponse<VoteResponse> update(@PathVariable(name = "id") Long id,
                                             @RequestBody VoteUpdateRequest request) {
        return new BaseResponse<>(of(voteService.update(id, request)));
    }

    @GetMapping
    public BaseResponse<List<VoteResponse>> readAll(VoteSearch search) {
        return new BaseResponse<>(voteService.readAll(search).stream()
                .map(VoteResponse::of)
                .collect(toList()));
    }

    @GetMapping("/{id}")
    public BaseResponse<VoteResponse> read(@PathVariable(name = "id") Long id) {
        return new BaseResponse<>(of(voteService.read(id)));
    }
}

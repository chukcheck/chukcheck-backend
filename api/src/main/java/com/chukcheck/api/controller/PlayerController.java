package com.chukcheck.api.controller;

import com.chukcheck.core.dto.request.create.PlayerCreateRequest;
import com.chukcheck.core.dto.request.update.PlayerUpdateRequest;
import com.chukcheck.core.dto.response.BaseResponse;
import com.chukcheck.core.dto.response.PlayerResponse;
import com.chukcheck.core.dto.search.PlayerSearch;
import com.chukcheck.api.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.chukcheck.core.dto.response.PlayerResponse.of;
import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/player")
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping
    public BaseResponse<PlayerResponse> create(@RequestBody PlayerCreateRequest request) {
        return new BaseResponse<>(of(playerService.create(request)));
    }

    @PutMapping("/{id}")
    public BaseResponse<PlayerResponse> update(@PathVariable(name = "id") Long id,
                                               @RequestBody PlayerUpdateRequest request) {
        return new BaseResponse<>(of(playerService.update(id, request)));
    }

    @GetMapping
    public BaseResponse<List<PlayerResponse>> readAll(PlayerSearch search) {
        return new BaseResponse<>(playerService.readSearch(search).stream()
                .map(PlayerResponse::of)
                .collect(toList()));
    }

    @GetMapping("/{id}")
    public BaseResponse<PlayerResponse> read(@PathVariable(name = "id") Long id) {
        return new BaseResponse<>(of(playerService.readDetail(id)));
    }

}

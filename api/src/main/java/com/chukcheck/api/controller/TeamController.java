package com.chukcheck.api.controller;

import com.chukcheck.core.dto.request.create.TeamCreateRequest;
import com.chukcheck.core.dto.request.update.TeamUpdateRequest;
import com.chukcheck.core.dto.response.BaseResponse;
import com.chukcheck.core.dto.response.TeamResponse;
import com.chukcheck.core.dto.search.TeamSearch;
import com.chukcheck.api.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.chukcheck.core.dto.response.TeamResponse.of;
import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/team")
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public BaseResponse<TeamResponse> create(@RequestBody TeamCreateRequest teamCreateRequest) {
        return new BaseResponse<>(of(teamService.create(teamCreateRequest)));
    }

    @PutMapping("/{id}")
    public BaseResponse<TeamResponse> update(@PathVariable(name = "id") Long id,
                                             @RequestBody TeamUpdateRequest teamUpdateRequest) {
        return new BaseResponse<>(of(teamService.update(id, teamUpdateRequest)));
    }

    @GetMapping
    public BaseResponse<List<TeamResponse>> readAll(TeamSearch teamSearch) {
        return new BaseResponse<>(teamService.readSearch(teamSearch).stream()
                .map(TeamResponse::of)
                .collect(toList()));
    }

    @GetMapping("/{id}")
    public BaseResponse<TeamResponse> read(@PathVariable(name = "id") Long id) {
        return new BaseResponse<>(of(teamService.readDetail(id)));
    }
}

package com.chukcheck.api.controller;

import com.chukcheck.core.dto.request.create.MemberCreateRequest;
import com.chukcheck.core.dto.response.BaseResponse;
import com.chukcheck.core.dto.response.MemberResponse;
import com.chukcheck.core.dto.response.TeamResponse;
import com.chukcheck.core.dto.search.MemberSearch;
import com.chukcheck.api.service.MemberService;
import com.chukcheck.api.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.chukcheck.core.dto.response.MemberResponse.of;
import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;
    private final PlayerService playerService;

    @PostMapping
    public BaseResponse<MemberResponse> create(@RequestBody MemberCreateRequest request) {
        return new BaseResponse<>(of(memberService.create(request)));
    }

    @GetMapping
    public BaseResponse<List<MemberResponse>> readAll(MemberSearch search) {
        return new BaseResponse<>(memberService.readSearch(search).stream()
                .map(MemberResponse::of)
                .collect(toList()));
    }

    @GetMapping("/{id}")
    public BaseResponse<MemberResponse> read(@PathVariable(name = "id") Long id) {
        return new BaseResponse<>(of(memberService.readDetail(id)));
    }

    @GetMapping("/{id}/teams")
    public BaseResponse<List<TeamResponse>> teams(@PathVariable(name = "id") Long id) {
        return new BaseResponse<>(playerService.readTeamByMemberId(id).stream()
                .map(TeamResponse::of)
                .collect(toList()));
    }
}

package com.chukcheck.api.dto.search;

import com.chukcheck.api.entity.BaseStatus;
import com.chukcheck.api.entity.PlayerAuthority;
import com.chukcheck.api.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerSearch {

    private Long memberId;
    private Long teamId;
    private Position position;
    private BaseStatus status;
    private PlayerAuthority authority;

    public static PlayerSearch ofMemberId(Long memberId) {
        return PlayerSearch.builder().memberId(memberId).build();
    }
}

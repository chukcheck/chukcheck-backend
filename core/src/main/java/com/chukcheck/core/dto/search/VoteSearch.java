package com.chukcheck.core.dto.search;

import com.chukcheck.core.entity.VoteStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteSearch {

    private Long playerId;
    private Long matchId;
    private VoteStatus status;
}

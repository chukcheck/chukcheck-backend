package com.chukcheck.core.dto.search;

import com.chukcheck.core.entity.AttendStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendSearch {

    private Long playerId;
    private Long matchId;
    private AttendStatus status;
}

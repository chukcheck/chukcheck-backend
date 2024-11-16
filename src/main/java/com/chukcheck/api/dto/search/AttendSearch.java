package com.chukcheck.api.dto.search;

import com.chukcheck.api.entity.AttendStatus;
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

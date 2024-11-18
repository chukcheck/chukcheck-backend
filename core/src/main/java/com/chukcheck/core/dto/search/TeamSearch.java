package com.chukcheck.core.dto.search;

import com.chukcheck.core.entity.BaseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamSearch {

    private Long regionId;
    private String name;
    private BaseStatus status;
}

package com.chukcheck.api.dto.search;

import com.chukcheck.api.entity.BaseStatus;
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

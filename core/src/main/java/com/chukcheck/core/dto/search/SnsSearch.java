package com.chukcheck.core.dto.search;

import com.chukcheck.core.entity.SnsType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SnsSearch {

    private String uuid;
    private SnsType type;
}

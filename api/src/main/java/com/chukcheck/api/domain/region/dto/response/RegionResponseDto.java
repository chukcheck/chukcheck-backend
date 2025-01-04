package com.chukcheck.api.domain.region.dto.response;

import com.chukcheck.core.domain.region.entity.Region;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RegionResponseDto {

    private final Long regionId;
    private final String country;
    private final String city;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    public static RegionResponseDto of(Region region) {
        return RegionResponseDto.builder()
                .regionId(region.getId())
                .country(region.getCountry())
                .city(region.getCity())
                .createdDate(region.getCreatedDate())
                .updatedDate(region.getUpdatedDate())
                .build();
    }
}

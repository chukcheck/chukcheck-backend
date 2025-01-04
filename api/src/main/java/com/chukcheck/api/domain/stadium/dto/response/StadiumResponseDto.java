package com.chukcheck.api.domain.stadium.dto.response;

import com.chukcheck.core.domain.stadium.entity.Stadium;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StadiumResponseDto {

    private final Long stadiumId;
    private final String name;
    private final String address;
    private final double latitude;
    private final double longitude;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    public static StadiumResponseDto of(Stadium stadium) {
        return StadiumResponseDto.builder()
                .stadiumId(stadium.getId())
                .name(stadium.getName())
                .address(stadium.getAddress())
                .latitude(stadium.getLatitude())
                .longitude(stadium.getLongitude())
                .createdDate(stadium.getCreatedDate())
                .updatedDate(stadium.getUpdatedDate())
                .build();
    }
}

package com.chukcheck.core.domain.region.command;

import com.chukcheck.core.domain.region.entity.Region;

public record RegionCreateCommand(
        String country,
        String city
) {

    public Region toEntity() {
        return Region.builder()
                .country(country)
                .city(city)
                .build();
    }
}

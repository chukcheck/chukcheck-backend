package com.chukcheck.core.domain.stadium.command;

import com.chukcheck.core.domain.stadium.entity.Stadium;
import lombok.Builder;
import lombok.Getter;

public record StadiumCreateCommand(String name, String address, double latitude, double longitude) {

    public Stadium toEntity() {
        return Stadium.builder()
                .name(name)
                .address(address)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}

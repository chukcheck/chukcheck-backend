package com.chukcheck.api.domain.region.dto.request;

import com.chukcheck.core.domain.region.command.RegionCreateCommand;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class RegionCreateRequestDto {

    @NotEmpty
    private String country;

    @NotEmpty
    private String city;

    @Builder
    public RegionCreateRequestDto(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public RegionCreateCommand toCommand() {
        return RegionCreateCommand.builder()
                .country(country)
                .city(city)
                .build();
    }
}

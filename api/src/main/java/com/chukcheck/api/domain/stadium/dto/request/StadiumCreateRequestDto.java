package com.chukcheck.api.domain.stadium.dto.request;

import com.chukcheck.core.domain.stadium.command.StadiumCreateCommand;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class StadiumCreateRequestDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;

    private double latitude;

    private double longitude;

    @Builder
    public StadiumCreateRequestDto(String name, String address, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public StadiumCreateCommand toCommand() {
        return new StadiumCreateCommand(name, address, latitude, longitude);
    }
}

package com.chukcheck.api.domain.sns.dto.request;

import com.chukcheck.core.domain.sns.command.SnsCreateCommand;
import com.chukcheck.core.domain.sns.model.SnsType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class SnsCreateRequestDto {

    @NotEmpty
    private String uuid;

    private SnsType type;

    @Builder
    public SnsCreateRequestDto(String uuid, SnsType type) {
        this.uuid = uuid;
        this.type = type;
    }

    public SnsCreateCommand toCommand() {
        return new SnsCreateCommand(uuid, type);
    }
}

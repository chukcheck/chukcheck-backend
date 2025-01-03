package com.chukcheck.api.domain.sns.dto.request;

import com.chukcheck.core.domain.sns.command.SnsSearchCommand;
import com.chukcheck.core.domain.sns.model.SnsType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SnsSearchRequestDto {

    private String uuid;
    private SnsType type;

    public SnsSearchCommand toCommand() {
        return new SnsSearchCommand(uuid, type);
    }
}

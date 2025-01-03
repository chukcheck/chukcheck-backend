package com.chukcheck.core.domain.sns.command;

import com.chukcheck.core.domain.sns.model.SnsType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public record SnsSearchCommand(
        String uuid,
        SnsType type
) {
}

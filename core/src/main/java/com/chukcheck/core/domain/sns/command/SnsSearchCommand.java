package com.chukcheck.core.domain.sns.command;

import com.chukcheck.core.domain.sns.model.SnsType;

public record SnsSearchCommand(
        String uuid,
        SnsType type
) {
}

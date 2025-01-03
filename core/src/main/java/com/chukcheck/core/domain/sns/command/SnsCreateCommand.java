package com.chukcheck.core.domain.sns.command;

import com.chukcheck.core.domain.sns.entity.Sns;
import com.chukcheck.core.domain.sns.model.SnsType;
import lombok.Builder;
import lombok.Getter;

public record SnsCreateCommand(
        String uuid,
        SnsType type
) {

    public Sns toEntity() {
        return Sns.builder()
                .uuid(uuid)
                .type(type)
                .build();
    }
}

package com.chukcheck.core.domain.vote.model;

import com.chukcheck.core.common.model.EnumType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VoteStatus implements EnumType {

    WAIT("미참여"),
    TRUE("참석"),
    FALSE("불참");

    private final String description;

    @Override
    public String getName() {
        return name();
    }
}

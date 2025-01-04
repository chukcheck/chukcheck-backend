package com.chukcheck.core.domain.player.model;

import com.chukcheck.core.common.model.EnumType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Position implements EnumType {

    FW("공격수"),
    MF("미드필더"),
    DF("수비수"),
    GK("골키퍼");

    private final String description;

    @Override
    public String getName() {
        return name();
    }
}

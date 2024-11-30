package com.chukcheck.core.dto.request.update;

import com.chukcheck.core.entity.BaseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class TeamUpdateRequest {

    private BaseStatus status;

    @Builder
    public TeamUpdateRequest(BaseStatus status) {
        this.status = status;
    }
}

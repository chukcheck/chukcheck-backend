package com.chukcheck.api.dto.request.update;

import com.chukcheck.api.entity.VoteStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class VoteUpdateRequest {

    private VoteStatus status;

    public VoteUpdateRequest(VoteStatus status) {
        this.status = status;
    }
}

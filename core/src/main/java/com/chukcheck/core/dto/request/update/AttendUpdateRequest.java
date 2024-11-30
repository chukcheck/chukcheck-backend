package com.chukcheck.core.dto.request.update;

import com.chukcheck.core.entity.AttendStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class AttendUpdateRequest {

    private AttendStatus status;

    public AttendUpdateRequest(AttendStatus status) {
        this.status = status;
    }
}

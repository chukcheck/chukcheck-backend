package com.chukcheck.api.domain.attend.dto.request;

import com.chukcheck.core.domain.attend.command.AttendUpdateCommand;
import com.chukcheck.core.domain.attend.model.AttendStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class AttendUpdateRequestDto {

    private AttendStatus status;

    public AttendUpdateRequestDto(AttendStatus status) {
        this.status = status;
    }

    public AttendUpdateCommand toCommand(Long id) {
        return new AttendUpdateCommand(id, status);
    }
}

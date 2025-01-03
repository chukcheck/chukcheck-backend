package com.chukcheck.api.domain.vote.dto.request;

import com.chukcheck.core.domain.vote.command.VoteUpdateCommand;
import com.chukcheck.core.domain.vote.model.VoteStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class VoteUpdateRequestDto {

    private VoteStatus status;

    public VoteUpdateRequestDto(VoteStatus status) {
        this.status = status;
    }

    public VoteUpdateCommand toCommand(Long id) {
        return new VoteUpdateCommand(id, status);
    }

}

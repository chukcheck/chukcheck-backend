package com.chukcheck.api.domain.member.dto.request;

import com.chukcheck.core.domain.member.command.MemberSearchCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSearchRequestDto {

    private Long snsId;
    private String name;
    private String email;

    public MemberSearchCommand toCommand() {
        return new MemberSearchCommand(snsId, name, email);
    }
}

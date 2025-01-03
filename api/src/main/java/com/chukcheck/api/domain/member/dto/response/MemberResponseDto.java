package com.chukcheck.api.domain.member.dto.response;

import com.chukcheck.api.domain.sns.dto.response.SnsResponseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.chukcheck.core.domain.member.entity.Member;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@JsonInclude(NON_NULL)
public class MemberResponseDto {

    private final Long memberId;
    private final String name;
    private final String email;
    private final LocalDate birthDate;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    private final SnsResponseDto sns;

    public static MemberResponseDto of(Member member) {
        return MemberResponseDto.builder()
                .memberId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .birthDate(member.getBirthDate())
                .createdDate(member.getCreatedDate())
                .updatedDate(member.getUpdatedDate())
                .sns(SnsResponseDto.of(member.getSns()))
                .build();
    }
}

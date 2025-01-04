package com.chukcheck.api.domain.member.dto.request;

import com.chukcheck.core.domain.member.command.MemberCreateCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class MemberCreateRequestDto {

    @NotEmpty
    private Long snsId;

    @NotEmpty
    private String name;

    @Email
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Builder
    public MemberCreateRequestDto(Long snsId, String name, String email, LocalDate birthDate) {
        this.snsId = snsId;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public MemberCreateCommand toCommand() {
        return new MemberCreateCommand(snsId, name, email, birthDate);
    }
}

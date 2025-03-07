package com.chukcheck.core.dto.request.create;

import com.chukcheck.core.entity.Member;
import com.chukcheck.core.entity.Sns;
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
public class MemberCreateRequest {

    @NotEmpty
    private Long snsId;

    @NotEmpty
    private String name;

    @Email
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Builder
    public MemberCreateRequest(Long snsId, String name, String email, LocalDate birthDate) {
        this.snsId = snsId;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Member toEntity(Sns sns) {
        return Member.builder()
                .sns(sns)
                .name(name)
                .email(email)
                .birthDate(birthDate)
                .build();
    }
}

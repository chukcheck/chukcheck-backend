package com.chukcheck.api.dto.request.create;

import com.chukcheck.api.entity.Sns;
import com.chukcheck.api.entity.SnsType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class SnsCreateRequest {

    @NotEmpty
    private String uuid;

    private SnsType type;

    @Builder
    public SnsCreateRequest(String uuid, SnsType type) {
        this.uuid = uuid;
        this.type = type;
    }

    public Sns toEntity() {
        return Sns.builder()
                .uuid(uuid)
                .type(type)
                .build();
    }
}

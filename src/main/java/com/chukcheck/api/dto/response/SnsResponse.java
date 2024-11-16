package com.chukcheck.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.chukcheck.api.entity.Sns;
import com.chukcheck.api.entity.SnsType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@JsonInclude(NON_NULL)
public class SnsResponse {

    private final Long snsId;
    private final String uuid;
    private final SnsType type;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    public static SnsResponse of(Sns sns) {
        return SnsResponse.builder()
                .snsId(sns.getId())
                .uuid(sns.getUuid())
                .type(sns.getType())
                .createdDate(sns.getCreatedDate())
                .updatedDate(sns.getUpdatedDate())
                .build();
    }
}

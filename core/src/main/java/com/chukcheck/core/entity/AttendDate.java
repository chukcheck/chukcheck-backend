package com.chukcheck.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class AttendDate {

    @Column(name = "attend_deadline_date")
    private LocalDateTime deadlineDate;

    @Builder
    public AttendDate(LocalDateTime deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public static AttendDate of(LocalDateTime endDate) {
        return new AttendDate(endDate);
    }
}

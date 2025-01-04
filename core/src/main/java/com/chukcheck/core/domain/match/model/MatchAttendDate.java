package com.chukcheck.core.domain.match.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class MatchAttendDate {

    @Column(name = "attend_deadline_date")
    private LocalDateTime deadlineDate;

    private MatchAttendDate(LocalDateTime deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public static MatchAttendDate of(LocalDateTime deadlineDate) {
        return new MatchAttendDate(deadlineDate);
    }

    public void updateDeadlineDate(LocalDateTime deadlineDate) {
        if (deadlineDate != null) {
            this.deadlineDate = deadlineDate;
        }
    }
}

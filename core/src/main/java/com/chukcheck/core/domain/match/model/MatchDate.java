package com.chukcheck.core.domain.match.model;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class MatchDate {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private MatchDate(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static MatchDate of(LocalDateTime startDate, LocalDateTime endDate) {
        return new MatchDate(startDate, endDate);
    }

    public void updateStartDate(LocalDateTime startDate) {
        if (startDate == null) {
            return;
        }

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("매치시작일자는 매치종료일자보다 이후일 수 없습니다.");
        }

        this.startDate = startDate;
    }

    public void updateEndDate(LocalDateTime endDate) {
        if (endDate == null) {
            return;
        }

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("매치종료일자는 매치시작일자보다 이전일 수 없습니다.");
        }

        this.endDate = endDate;
    }
}

package com.chukcheck.core.domain.match.model;

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
public class MatchVoteDate {

    @Column(name = "vote_start_date")
    private LocalDateTime startDate;

    @Column(name = "vote_end_date")
    private LocalDateTime endDate;

    private MatchVoteDate(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static MatchVoteDate of(LocalDateTime startDate, LocalDateTime endDate) {
        return new MatchVoteDate(startDate, endDate);
    }

    public void updateStartDate(LocalDateTime startDate) {
        if (startDate == null) {
            return;
        }

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("투표시작일자는 투표종료일자보다 이후일 수 없습니다.");
        }

        this.startDate = startDate;
    }

    public void updateEndDate(LocalDateTime endDate) {
        if (endDate == null) {
            return;
        }

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("투표종료일자는 투표시작일자보다 이전일 수 없습니다.");
        }

        this.endDate = endDate;
    }
}

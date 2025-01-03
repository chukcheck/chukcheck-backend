package com.chukcheck.core.domain.attend.repository;

import com.chukcheck.core.domain.attend.command.AttendSearchCommand;
import com.chukcheck.core.domain.attend.entity.Attend;

import java.util.List;
import java.util.Optional;

public interface AttendQueryRepository {

    List<Attend> findQueryBySearch(AttendSearchCommand command);

    Optional<Attend> findQueryById(Long id);
}

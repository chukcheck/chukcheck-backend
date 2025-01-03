package com.chukcheck.core.domain.attend.application;

import com.chukcheck.core.domain.attend.command.AttendSearchCommand;
import com.chukcheck.core.domain.attend.entity.Attend;

import java.util.Collection;
import java.util.List;

public interface AttendReader {

    Attend findById(Long id);

    Attend findQueryById(Long id);

    List<Attend> findQueryBySearch(AttendSearchCommand command);
}

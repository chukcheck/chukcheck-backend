package com.chukcheck.core.domain.attend.application;

import com.chukcheck.core.domain.attend.entity.Attend;

public interface AttendWriter {

    Attend save(Attend entity);
}

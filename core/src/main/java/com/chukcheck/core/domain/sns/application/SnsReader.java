package com.chukcheck.core.domain.sns.application;

import com.chukcheck.core.domain.sns.command.SnsSearchCommand;
import com.chukcheck.core.domain.sns.entity.Sns;
import com.chukcheck.core.domain.sns.model.SnsType;

import java.util.List;

public interface SnsReader {

    Sns findById(Long id);

    Sns findNullableByUuidAndType(String uuid, SnsType type);

    List<Sns> findQueryBySearch(SnsSearchCommand command);
}

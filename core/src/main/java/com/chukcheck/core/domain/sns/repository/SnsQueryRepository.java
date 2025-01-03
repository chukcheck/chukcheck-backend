package com.chukcheck.core.domain.sns.repository;

import com.chukcheck.core.domain.sns.command.SnsSearchCommand;
import com.chukcheck.core.domain.sns.entity.Sns;

import java.util.List;

public interface SnsQueryRepository {

    List<Sns> findQueryBySearch(SnsSearchCommand search);
}

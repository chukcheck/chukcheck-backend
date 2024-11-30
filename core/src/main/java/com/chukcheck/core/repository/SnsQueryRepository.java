package com.chukcheck.core.repository;

import com.chukcheck.core.dto.search.SnsSearch;
import com.chukcheck.core.entity.Sns;

import java.util.List;

public interface SnsQueryRepository {

    List<Sns> findQueryBySearch(SnsSearch search);
}

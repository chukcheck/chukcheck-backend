package com.chukcheck.api.repository;

import com.chukcheck.api.dto.search.SnsSearch;
import com.chukcheck.api.entity.Sns;

import java.util.List;

public interface SnsQueryRepository {

    List<Sns> findQueryBySearch(SnsSearch search);
}

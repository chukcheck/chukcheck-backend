package com.chukcheck.core.repository;

import com.chukcheck.core.dto.search.AttendSearch;
import com.chukcheck.core.entity.Attend;

import java.util.List;
import java.util.Optional;

public interface AttendQueryRepository {

    List<Attend> findQueryBySearch(AttendSearch search);
    Optional<Attend> findQueryById(Long id);
}

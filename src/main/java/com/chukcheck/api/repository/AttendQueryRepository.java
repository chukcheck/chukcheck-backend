package com.chukcheck.api.repository;

import com.chukcheck.api.dto.search.AttendSearch;
import com.chukcheck.api.entity.Attend;

import java.util.List;
import java.util.Optional;

public interface AttendQueryRepository {

    List<Attend> findQueryBySearch(AttendSearch search);
    Optional<Attend> findQueryById(Long id);
}

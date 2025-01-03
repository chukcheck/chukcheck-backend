package com.chukcheck.core.domain.region.application;

import com.chukcheck.core.domain.region.entity.Region;

import java.util.List;

public interface RegionReader {

    Region findNullableByCountryAndCity(String country, String city);

    List<Region> findAll();

    Region findById(Long id);
}

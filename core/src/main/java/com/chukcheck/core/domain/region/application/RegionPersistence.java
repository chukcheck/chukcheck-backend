package com.chukcheck.core.domain.region.application;

import com.chukcheck.core.domain.region.entity.Region;
import com.chukcheck.core.domain.region.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RegionPersistence implements RegionReader, RegionWriter {

    private final RegionRepository repository;

    @Override
    public Region findNullableByCountryAndCity(String country, String city) {
        return repository.findByCountryAndCity(country, city).orElse(null);
    }

    @Override
    public List<Region> findAll() {
        return repository.findAll();
    }

    @Override
    public Region save(Region entity) {
        return repository.save(entity);
    }
}

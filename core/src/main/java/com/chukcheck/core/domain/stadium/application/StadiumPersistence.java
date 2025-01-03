package com.chukcheck.core.domain.stadium.application;

import com.chukcheck.core.common.exception.CoreError;
import com.chukcheck.core.common.exception.CoreException;
import com.chukcheck.core.domain.stadium.entity.Stadium;
import com.chukcheck.core.domain.stadium.repository.StadiumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StadiumPersistence implements StadiumReader, StadiumWriter {

    private final StadiumRepository repository;

    @Override
    public Stadium findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CoreException(CoreError.NOT_FOUND_DOMAIN));
    }

    @Override
    public List<Stadium> findAll() {
        return repository.findAll();
    }

    @Override
    public Stadium save(Stadium entity) {
        return repository.save(entity);
    }
}

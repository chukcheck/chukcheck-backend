package com.chukcheck.core.domain.sns.application;

import com.chukcheck.core.common.exception.CoreError;
import com.chukcheck.core.common.exception.CoreException;
import com.chukcheck.core.domain.sns.command.SnsSearchCommand;
import com.chukcheck.core.domain.sns.entity.Sns;
import com.chukcheck.core.domain.sns.model.SnsType;
import com.chukcheck.core.domain.sns.repository.SnsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SnsPersistence implements SnsReader, SnsWriter {

    private final SnsRepository repository;

    @Override
    public Sns findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CoreException(CoreError.NOT_FOUND_DOMAIN));
    }

    @Override
    public Sns findNullableByUuidAndType(String uuid, SnsType type) {
        return repository.findByUuidAndType(uuid, type).orElse(null);
    }

    @Override
    public List<Sns> findQueryBySearch(SnsSearchCommand command) {
        return repository.findQueryBySearch(command);
    }

    @Override
    public Sns save(Sns entity) {
        return repository.save(entity);
    }
}

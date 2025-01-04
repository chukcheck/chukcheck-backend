package com.chukcheck.core.domain.attend.application;

import com.chukcheck.core.common.exception.CoreError;
import com.chukcheck.core.common.exception.CoreException;
import com.chukcheck.core.domain.attend.command.AttendSearchCommand;
import com.chukcheck.core.domain.attend.entity.Attend;
import com.chukcheck.core.domain.attend.repository.AttendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AttendPersistence implements AttendWriter, AttendReader {

    private final AttendRepository repository;

    @Override
    public Attend findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CoreException(CoreError.NOT_FOUND_DOMAIN));
    }

    @Override
    public Attend findQueryById(Long id) {
        return repository.findQueryById(id).orElseThrow(() -> new CoreException(CoreError.NOT_FOUND_DOMAIN));
    }

    @Override
    public List<Attend> findQueryBySearch(AttendSearchCommand command) {
        return repository.findQueryBySearch(command);
    }

    @Override
    public Attend save(Attend entity) {
        return repository.save(entity);
    }
}

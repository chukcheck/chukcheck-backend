package com.chukcheck.api.service;

import com.chukcheck.core.entity.Admin;
import com.chukcheck.core.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    @Transactional(readOnly = true)
    public Admin read(Long id) {
        return adminRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not exist admin"));
    }
}

package com.chukcheck.scheduler.service;

import com.chukcheck.core.dto.request.create.TeamCreateRequest;
import com.chukcheck.core.entity.Region;
import com.chukcheck.core.entity.Team;
import com.chukcheck.core.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final RegionService regionService;

    public Team create(TeamCreateRequest request) {
        validateName(request);

        return teamRepository.save(request.toEntity(findRegion(request)));
    }

    @Transactional(readOnly = true)
    public Team read(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not exist team"));
    }

    private Region findRegion(TeamCreateRequest request) {
        return regionService.read(request.getRegionId());
    }

    private void validateName(TeamCreateRequest request) {
        if (teamRepository.findByName(request.getName()).isPresent()) {
            throw new IllegalArgumentException("Already team name");
        }
    }
}

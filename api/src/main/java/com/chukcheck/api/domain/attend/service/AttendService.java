package com.chukcheck.api.domain.attend.service;

import com.chukcheck.api.domain.attend.dto.response.AttendResponseDto;
import com.chukcheck.core.domain.attend.application.AttendReader;
import com.chukcheck.core.domain.attend.application.AttendWriter;
import com.chukcheck.core.domain.attend.command.AttendCreateCommand;
import com.chukcheck.core.domain.attend.command.AttendSearchCommand;
import com.chukcheck.core.domain.attend.command.AttendUpdateCommand;
import com.chukcheck.core.domain.attend.entity.Attend;
import com.chukcheck.core.domain.match.application.MatchReader;
import com.chukcheck.core.domain.match.entity.Match;
import com.chukcheck.core.domain.player.application.PlayerReader;
import com.chukcheck.core.domain.player.entity.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AttendService implements AttendUseCase {

    private final AttendReader attendReader;
    private final AttendWriter attendWriter;
    private final PlayerReader playerReader;
    private final MatchReader matchReader;

    public AttendResponseDto create(AttendCreateCommand command) {
        Player player = playerReader.findById(command.playerId());
        Match match = matchReader.findById(command.matchId());

        Attend attend = attendWriter.save(command.toEntity(player, match));

        return AttendResponseDto.of(attend);
    }

    public AttendResponseDto update(AttendUpdateCommand command) {
        Attend attend = attendReader.findById(command.id());

        attend.updateStatus(command.status());

        return AttendResponseDto.of(attend);
    }

    @Transactional(readOnly = true)
    public List<AttendResponseDto> readAll(AttendSearchCommand command) {
        return attendReader.findQueryBySearch(command).stream()
                .map(AttendResponseDto::of)
                .toList();
    }

    @Transactional(readOnly = true)
    public AttendResponseDto read(Long id) {
        Attend attend = attendReader.findQueryById(id);
        return AttendResponseDto.of(attend);
    }
}

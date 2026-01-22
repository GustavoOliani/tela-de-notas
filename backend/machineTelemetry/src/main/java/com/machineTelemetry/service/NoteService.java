package com.machineTelemetry.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.machineTelemetry.controller.dto.NoteDTO;
import com.machineTelemetry.model.NoteEntity;
import com.machineTelemetry.repository.NoteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    
    @Transactional
    public List<NoteDTO> createNotes(List<NoteDTO> noteDto) {
        List<NoteEntity> notesToSave = noteDto.stream()
            .map(this::convertToEntity)
            .toList();
        
        List<NoteEntity> savedEntities = noteRepository.saveAll(notesToSave);

        return savedEntities.stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public List<NoteDTO> findNotesBy(String site, String equipment, LocalDate start, LocalDate end) {
        Instant startInstant = (start != null) ? start.atStartOfDay(ZoneOffset.UTC).toInstant() : null;
        Instant endInstant = (end != null) ? end.atTime(LocalTime.MAX).atZone(ZoneOffset.UTC).toInstant() : null;

        return noteRepository.findByFilters(site, equipment, startInstant, endInstant)
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    private NoteEntity convertToEntity(NoteDTO dto) {
        NoteEntity note = new NoteEntity();
        note.setId(dto.getId());
        note.setSite(dto.getSite());
        note.setEquipment(dto.getEquipment());
        note.setVariable(dto.getVariable());
        note.setTimestamp(dto.getTimestamp());
        note.setAuthor(dto.getAuthor());
        note.setMessage(dto.getMessage());
        return note;
    }

    private NoteDTO convertToResponseDTO(NoteEntity entity) {
        return new NoteDTO(
            entity.getId(),
            entity.getSite(),
            entity.getEquipment(),
            entity.getVariable(),
            entity.getTimestamp(),
            entity.getAuthor(),
            entity.getMessage()
        );
    }
}

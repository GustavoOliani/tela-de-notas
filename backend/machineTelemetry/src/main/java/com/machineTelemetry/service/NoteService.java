package com.machineTelemetry.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.machineTelemetry.ExceptionHandler.BusinessException;
import com.machineTelemetry.model.dto.NoteDTO;
import com.machineTelemetry.model.entity.NoteEntity;
import com.machineTelemetry.model.filter.NoteFilters;
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

    public List<NoteDTO> findNotesBy(NoteFilters params) {
        LocalDate startDate = params.getStartDate();
        LocalDate endDate = params.getEndDate();
        
        validatePeriod(startDate, endDate);
        Long offset = setOffset(params.getPage(), params.getSize());
        return noteRepository.findByFilters(
            params.getSite(),
            params.getEquipment(),
            convertToInstantStart(startDate),
            convertToInstantStart(endDate),
            params.getSize(),
            offset
        ).stream().map(this::convertToResponseDTO).toList();
    }

    private Instant convertToInstantStart(LocalDate date){
        return (date != null) ? date.atStartOfDay(ZoneOffset.UTC).toInstant() : null;
    }

    private void validatePeriod(LocalDate start, LocalDate end) {
        if (start != null && end != null && start.isAfter(end)) {
            throw new BusinessException("A data de início não pode ser posterior à data de término da busca.");
        }
    }

    private Long setOffset(Integer page, Integer size){
        return (long) page * size;
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

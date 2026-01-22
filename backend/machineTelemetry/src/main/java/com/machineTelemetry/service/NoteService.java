package com.machineTelemetry.service;

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

    public NoteEntity convertToEntity(NoteDTO dto) {
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

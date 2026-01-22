package com.machineTelemetry.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.machineTelemetry.controller.dto.NoteDTO;
import com.machineTelemetry.service.NoteService;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {
    
    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<List<NoteDTO>> createNote(@RequestBody List<NoteDTO> request) {
        List<NoteDTO> response = noteService.createNotes(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<NoteDTO>> getNotes(
        @RequestParam(required = false) String site,
        @RequestParam(required = false) String equipment,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        // No Service, converteremos LocalDate para Instant
        List<NoteDTO> notes = noteService.findNotesBy(site, equipment, startDate, endDate);
        return ResponseEntity.ok(notes);
    }
}

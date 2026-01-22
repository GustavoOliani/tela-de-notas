package com.machineTelemetry.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.machineTelemetry.controller.dto.NoteDTO;
import com.machineTelemetry.service.NoteService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
    
}

package com.machineTelemetry.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.machineTelemetry.model.dto.NoteDTO;
import com.machineTelemetry.model.filter.NoteFilters;
import com.machineTelemetry.service.NoteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
@Tag(name = "Telemetria", description = "Endpoints para gerenciamento de notas de máquinas")
public class NoteController {
    
    private final NoteService noteService;

    @Operation(summary = "Cria uma nota", description = "Insere um registro de telemetria no banco de dados. Retorna um NoteDTO.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Nota criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados enviados são inválidos")
    })
    @PostMapping
    public ResponseEntity<List<NoteDTO>> createNote(@RequestBody @Valid List<NoteDTO> request) {
        List<NoteDTO> response = noteService.createNotes(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Lista as notas já registradas, podendo ser aplicados filtros", description = "Busca notas paginadas filtrando pelas propriedades em NoteFilters")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Parâmetros de filtro inválidos")
    })
    @GetMapping
    public ResponseEntity<List<NoteDTO>> getNotes(@Valid NoteFilters filters) {
        List<NoteDTO> notes = noteService.findNotesBy(filters);
        return ResponseEntity.ok(notes);
    }
}

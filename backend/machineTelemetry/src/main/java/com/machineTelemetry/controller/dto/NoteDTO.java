package com.machineTelemetry.controller.dto;

import java.time.Instant;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class NoteDTO {
    @NotNull
    private UUID id;

    @NotBlank
    private String site;

    @NotBlank
    private String equipment;

    @NotBlank
    private String variable;

    @NotNull
    private Instant timestamp;

    @NotBlank
    private String author;

    @NotBlank
    private String message;

    public NoteDTO(UUID id, String site, String equipment, String variable, Instant timestamp, String author, String message){
        this.id = id;
        this.site = site;
        this.equipment = equipment;
        this.variable = variable;
        this.timestamp = timestamp;
        this.author = author;
        this.message = message;
    }
}

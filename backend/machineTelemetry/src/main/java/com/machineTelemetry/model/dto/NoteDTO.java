package com.machineTelemetry.model.dto;

import java.time.Instant;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NoteDTO {
    @NotNull
    private UUID id;

    @NotBlank(message = "O site é obrigatório")
    private String site;

    @NotBlank(message = "O equipamento é obrigatório")
    private String equipment;

    @NotBlank(message = "A variável é obrigatória")
    private String variable;

    @NotNull
    private Instant timestamp;

    @NotBlank(message = "O autor é obrigatório")
    private String author;

    @Size(min = 5, message = "A mensagem deve conter pelo menos 5 caracteres")
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

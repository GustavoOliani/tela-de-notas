package com.machineTelemetry.model.dto;

import java.time.Instant;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NoteDTO {
    
    private UUID id;

    @Schema(description = "Nome do site/localidade/empresa", example = "Alpha-01")
    @NotBlank(message = "O site é obrigatório")
    private String site;

    @Schema(description = "Nome do equipamento que está enviando o dado", example = "Ar-condicionado")
    @NotBlank(message = "O equipamento é obrigatório")
    private String equipment;

    @Schema(description = "Tipo de informação que o sensor está enviando", example = "Tensão")
    @NotBlank(message = "A variável é obrigatória")
    private String variable;

    @Schema(description = "Horário da medição", example = "2024-08-01T18:48:37.381Z")
    @NotNull
    private Instant timestamp;

    @Schema(description = "Pessoa ou nomenclatura do dispositivo que envia o dado", example = "Leeroy Jenkins")
    @NotBlank(message = "O autor é obrigatório")
    private String author;

    @Schema(description = "Conteúdo da mensagem de telemetria", example = "Tensão acima do valor de segurança")
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

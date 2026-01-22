package com.machineTelemetry.model.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteFilters {

    @Parameter(description = "Nome do site/localidade/empresa", example = "Carnes e CIA")
    String site;

    @Parameter(description = "Nome do equipamento que está enviando o dado", example = "Refrigerador")
    String equipment;
    
    @Parameter(description = "Data inicial (ISO: YYYY-MM-DD), valor máximo sendo o dia atual", example = "2026-01-01")
    @PastOrPresent(message = "A data inicial não pode estar no futuro")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate startDate;
    
    @Parameter(description = "Data final (ISO: YYYY-MM-DD)", example = "2026-01-01")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate endDate;

    @Parameter(description = "Número da página (inicia em 0), valor padrão é 0", example = "0")
    @Min(value = 0, message = "A página não pode ser negativa")
    Integer page = 0;

    @Parameter(description = "Quantidade de registros por página, o valor padrão é 10", example = "10")
    @Min(value = 1, message = "O tamanho deve ser no mínimo 1") 
    @Max(value = 1000, message = "O tamanho máximo é 1000")
    Integer size = 10;
}

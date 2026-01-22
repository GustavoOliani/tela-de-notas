package com.machineTelemetry.model.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

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
    String site;

    String equipment;

    @PastOrPresent(message = "A data inicial não pode estar no futuro")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate endDate;

    @Min(value = 0, message = "A página não pode ser negativa")
    Integer page = 0;

    @Min(value = 1, message = "O tamanho deve ser no mínimo 1") 
    @Max(value = 1000, message = "O tamanho máximo é 1000")
    Integer size = 10;
}

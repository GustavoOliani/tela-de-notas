package com.machineTelemetry.model;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("notes")
public class NoteEntity {

    @Id
    private UUID id;

    private String site;
    private String equipment;
    private String variable;
    private Instant timestamp; 
    private String author;
    private String message;
}

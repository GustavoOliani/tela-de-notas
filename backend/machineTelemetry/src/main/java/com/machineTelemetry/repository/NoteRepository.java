package com.machineTelemetry.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

import com.machineTelemetry.model.NoteEntity;

@Repository
public interface NoteRepository extends ListCrudRepository<NoteEntity, UUID>{

}

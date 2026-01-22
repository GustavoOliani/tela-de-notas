package com.machineTelemetry.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.machineTelemetry.model.NoteEntity;

@Repository
public interface NoteRepository extends ListCrudRepository<NoteEntity, UUID>{

    @Query("""
        SELECT id, site, equipment, variable, timestamp, author, message FROM notes 
        WHERE (:site IS NULL OR site = :site)
          AND (:equipment IS NULL OR equipment = :equipment)
          AND (CAST(:startDate AS TIMESTAMPTZ) IS NULL OR timestamp >= :startDate)
          AND (CAST(:endDate AS TIMESTAMPTZ) IS NULL OR timestamp <= :endDate)
    """)
    List<NoteEntity> findByFilters(
        String site, 
        String equipment, 
        Instant startDate, 
        Instant endDate
    );
}

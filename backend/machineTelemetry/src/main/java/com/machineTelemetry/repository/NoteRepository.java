package com.machineTelemetry.repository;

import org.springframework.stereotype.Repository;

import com.machineTelemetry.model.entity.NoteEntity;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface NoteRepository extends ListCrudRepository<NoteEntity, UUID>, PagingAndSortingRepository<NoteEntity, UUID>{

    @Query("""
        SELECT id, site, equipment, variable, timestamp, author, message FROM notes 
        WHERE (:site IS NULL OR site = :site)
          AND (:equipment IS NULL OR equipment = :equipment)
          AND (CAST(:startDate AS TIMESTAMPTZ) IS NULL OR timestamp >= :startDate)
          AND (CAST(:endDate AS TIMESTAMPTZ) IS NULL OR timestamp <= :endDate)
        ORDER BY timestamp DESC
        LIMIT :size OFFSET :offset
    """)
    List<NoteEntity> findByFilters(
        String site, 
        String equipment, 
        Instant startDate, 
        Instant endDate,
        int size,
        long offset
    );
}

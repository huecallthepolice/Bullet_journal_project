package com.buletjournal.project.repository;

import com.buletjournal.project.model.EntryNote;
import com.buletjournal.project.model.Progress;
import com.buletjournal.project.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface JournalRepository extends JpaRepository<EntryNote, Integer> {
    List<EntryNote> findByNoteContainingIgnoreCase(String keyword);

    List<EntryNote> findByDate(Date date);

    @Query(value = "select * from journal s where s.note like %:keyword% or s.type like %:keyword% or s.progress like %:keyword% ", nativeQuery = true)
    List<EntryNote> findByKeyword(@Param("keyword") String keyword);


    @Query(value = "select * from journal s where  s.type like %:keyword% ", nativeQuery = true)
    List<EntryNote> findByType(@Param("keyword") String keyword);


    @Query(value = "select * from journal s where  s.progress like %:keyword% ", nativeQuery = true)
    List<EntryNote> findByProgress(@Param("keyword") String keyword);

    @Query(value = "select * from journal s where  s.important", nativeQuery = true)
    List<EntryNote> findByImportant();
    @Query(value = "select * from journal s where  s.important = False ", nativeQuery = true)
    List<EntryNote> findByNotImportant();
}

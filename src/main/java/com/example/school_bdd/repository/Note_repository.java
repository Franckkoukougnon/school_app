package com.example.school_bdd.repository;

import com.example.school_bdd.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Note_repository extends JpaRepository<Note, Long> {

}

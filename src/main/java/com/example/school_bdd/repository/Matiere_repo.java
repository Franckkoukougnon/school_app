package com.example.school_bdd.repository;

import com.example.school_bdd.entity.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Matiere_repo extends JpaRepository<Matiere, Long> {

}

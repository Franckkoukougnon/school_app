package com.example.school_bdd.repository;

import com.example.school_bdd.entity.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Etablissement_repo extends JpaRepository<Etablissement, Long> {
}

package com.example.school_bdd.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Eleve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private int matricule;

    @Column(name = "photo_url", nullable = true)
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    @JsonBackReference(value = "classe-eleves")
    private Classe classe;

    @OneToMany(mappedBy = "eleve", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "note-eleve")
    private List<Note> notes;


}
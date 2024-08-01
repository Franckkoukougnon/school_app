package com.example.school_bdd.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Double note;

    private String appreciation;


    @ManyToOne()
    @JsonBackReference(value = "matiere-notes")
    private Matiere matiere;

    @ManyToOne
    @JoinColumn(name = "eleve_id")
    @JsonBackReference(value = "eleve-notes")
    private Eleve eleve;





}

package com.example.school_bdd.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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


    @ManyToOne
    @JoinColumn(name = "matiere_id")
    @JsonBackReference(value = "matiere-notes")
    private Matiere matiere;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "eleve_id")
    @JsonBackReference(value = "note-eleve")
    private Eleve eleve;


}

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
public class Matiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private double coefficient;


    @OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "matiere-notes")
    private List <Note> notes;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    @JsonBackReference(value = "classe-matieres")
    private Classe classe;








}

package com.ejercicio.Ejercicio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "raza")
@Getter
@Setter
public class Raza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "color")
    private String color;

    @Column(name = "precio")
    private float precio;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "raza")
    @JsonIgnore
    private Set<Animal> animal;
}

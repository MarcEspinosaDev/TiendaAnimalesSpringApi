package com.ejercicio.Ejercicio.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "animal")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "nombre"
)
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "edad")
    private int edad;

    @Column(name = "precio")
    private float precio;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "raza_id", nullable = false)
    private Raza raza;

    public Animal(String nombre, int edad, float precio) {
        this.nombre = nombre;
        this.edad = edad;
        this.precio = precio;
    }

    public Animal() {
    }
}

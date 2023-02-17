package com.ejercicio.Ejercicio.dto;

import com.ejercicio.Ejercicio.entity.Raza;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDto {
    @NotBlank
    private String nombre;
    private int edad;
    private float precio;
    private Raza raza;
}

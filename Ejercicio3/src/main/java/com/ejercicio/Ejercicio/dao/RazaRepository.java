package com.ejercicio.Ejercicio.dao;

import com.ejercicio.Ejercicio.entity.Raza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface RazaRepository extends JpaRepository<Raza, Long> {
    Optional<Raza> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}

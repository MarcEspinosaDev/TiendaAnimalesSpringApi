package com.ejercicio.Ejercicio.dao;

import com.ejercicio.Ejercicio.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findAllByNombre(String nombre);
    boolean existsAllByNombre(String nombre);
}

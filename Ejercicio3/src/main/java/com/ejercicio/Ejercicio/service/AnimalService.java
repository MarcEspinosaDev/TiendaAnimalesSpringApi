package com.ejercicio.Ejercicio.service;

import com.ejercicio.Ejercicio.dao.AnimalRepository;
import com.ejercicio.Ejercicio.entity.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AnimalService {
    @Autowired
    AnimalRepository animalRepository;

    public List<Animal> list() {return animalRepository.findAll();}
    public Optional<Animal> getOne(long id){return animalRepository.findById(id);}
    public List<Animal> getAllByNombre(String nombre){return animalRepository.findAllByNombre(nombre);}
    public void save(Animal animal){animalRepository.save(animal);}
    public void delete(long id) {animalRepository.deleteById(id);}
    public boolean existById(long id) {return animalRepository.existsById(id);}
    public boolean existByNombre(String nombre){return animalRepository.existsAllByNombre(nombre);}
}

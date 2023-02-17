package com.ejercicio.Ejercicio.controller;

import com.ejercicio.Ejercicio.dto.AnimalDto;
import com.ejercicio.Ejercicio.dto.Mensaje;
import com.ejercicio.Ejercicio.entity.Animal;
import com.ejercicio.Ejercicio.entity.Raza;
import com.ejercicio.Ejercicio.service.AnimalService;
import com.ejercicio.Ejercicio.service.RazaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/animales")
@CrossOrigin
public class AnimalController {
    @Autowired
    AnimalService animalService;
    @Autowired
    RazaService razaService;

    //GET
    @GetMapping("/list")
    public ResponseEntity<List<Animal>> List(){
        List<Animal> list = animalService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    //GET_ID
    @GetMapping("/list/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id) {
        if (!animalService.existById(id))
            return new ResponseEntity<>(new Mensaje("no existe el id"), HttpStatus.NOT_FOUND);
        if (animalService.getOne(id).isPresent()){
            Animal animal = animalService.getOne(id).get();
            return new ResponseEntity<>(animal, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Mensaje("No existe el id"), HttpStatus.NOT_FOUND);
    }
    //GET_NOMBRE
    @GetMapping("/listname/{nombre}")
    public ResponseEntity<?> getByNombre(@PathVariable("nombre") String nombre) {
        if (!animalService.existByNombre(nombre))
            return new ResponseEntity<>(new Mensaje("no existe el id"), HttpStatus.NOT_FOUND);
        List<Animal> animales = animalService.getAllByNombre(nombre);
        return new ResponseEntity<>(animales, HttpStatus.OK);
    }
    //POST
    @PostMapping("/create")
    public ResponseEntity<?> create (@RequestBody AnimalDto animalDto){
        if (StringUtils.isBlank(animalDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("no existe el animal"), HttpStatus.NOT_FOUND);
        Raza raza;
        Animal animal = new Animal(animalDto.getNombre(), animalDto.getEdad(), animalDto.getPrecio());

        if (razaService.getByNombre(animalDto.getRaza().getNombre()).isPresent()){
            raza = razaService.getByNombre(animalDto.getRaza().getNombre()).get();
            animal.setRaza(raza);
        } else {
            if (StringUtils.isBlank(animalDto.getRaza().getNombre()))
                return new ResponseEntity<>(new Mensaje("raza obligatoria"), HttpStatus.NOT_FOUND);
            Raza razaNew = animalDto.getRaza();
            animal.setRaza(razaNew);
            if (razaNew.getAnimal() != null){
                razaNew.getAnimal().add(animal);
            } else {
                Set<Animal> animales = new HashSet<>();
                animales.add(animal);
                razaNew.setAnimal(animales);
            }
        }
        animalService.save(animal);
        return new ResponseEntity<>(new Mensaje("animal creado"), HttpStatus.OK);
    }
    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody AnimalDto animalDto){
        if (!animalService.existById(id))
            return new ResponseEntity<>(new Mensaje("El id no existe"), HttpStatus.NOT_FOUND);
        if (StringUtils.isBlank(animalDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre esta en blanco"), HttpStatus.BAD_REQUEST);
        Animal animal = animalService.getOne(id).get();
        animal.setNombre(animalDto.getNombre());
        animal.setEdad(animalDto.getEdad());
        animal.setPrecio(animalDto.getPrecio());
        animal.setRaza(animalDto.getRaza());

        if (razaService.existByNombre(animalDto.getRaza().getNombre())) {
            Raza raza = razaService.getByNombre(animalDto.getRaza().getNombre()).get();
            raza.setPrecio(animalDto.getRaza().getPrecio());
            raza.setColor(animalDto.getRaza().getColor());
            animal.setRaza(raza);
        } else {
            animal.setRaza(animalDto.getRaza());
        }

        animalService.save(animal);
        return new ResponseEntity<>(new Mensaje("animal actualizado"), HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        if (!animalService.existById(id))
            return new ResponseEntity<>(new Mensaje("El id no existe"), HttpStatus.NOT_FOUND);
        animalService.delete(id);
        return new ResponseEntity<>(new Mensaje("animal borrado con el id "+id), HttpStatus.OK);
    }
}

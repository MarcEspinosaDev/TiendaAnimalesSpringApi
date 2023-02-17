package com.ejercicio.Ejercicio.service;

import com.ejercicio.Ejercicio.dao.RazaRepository;
import com.ejercicio.Ejercicio.entity.Raza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RazaService {
    @Autowired
    RazaRepository razaRepository;

    public List<Raza> list() {return razaRepository.findAll();}
    public Optional<Raza> getOne(long id){return razaRepository.findById(id);}
    public Optional<Raza> getByNombre(String nombre){return razaRepository.findByNombre(nombre);}
    public void save(Raza raza){razaRepository.save(raza);}
    public void delete(long id) {razaRepository.deleteById(id);}
    public boolean existById(long id) {return razaRepository.existsById(id);}
    public boolean existByNombre(String nombre){return razaRepository.existsByNombre(nombre);}
}

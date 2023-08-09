package com.example.demo.entity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.models.SuperHeroe;
import com.example.demo.entity.repository.SuperHeroeRepository;

@Service
public class SuperHeroeServiceImpl implements ISuperHeroeService {
    @Autowired
    private SuperHeroeRepository heroeRepository;

    @Override
    public List<SuperHeroe>listarSuperhH() {
        return (List<SuperHeroe>) heroeRepository.findAll();
    }
    @Override
    public void guardarSuperH(SuperHeroe superHeroe) {
        heroeRepository.save(superHeroe);
    }
    @Override
    public SuperHeroe buscarSuperHPorId(Long id_superheroe) {
        return heroeRepository.findById(id_superheroe).orElse(null);
    }
    @Override
    public void eliminarSuperH(Long id_superheroe) {
        heroeRepository.deleteById(id_superheroe);
    }
}

package com.example.demo.entity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.models.Planeta;
import com.example.demo.entity.repository.PlanetaRepository;

@Service
public class PlanetaServiceImpl implements IPlanetaService {
    @Autowired
    private PlanetaRepository planetaRepository;
        
    @Override
    public List<Planeta>listarPlaneta() {
        return (List<Planeta>) planetaRepository.findAll();
    }
    @Override
    public void guardarPlaneta(Planeta planeta) {
        planetaRepository.save(planeta);
    }
    @Override
    public Planeta buscarPlanetaPorId(Long id_planeta) {
        return planetaRepository.findById(id_planeta).orElse(null);
    }
    @Override
    public void eliminarPlaneta(Long id_planeta) {
        planetaRepository.deleteById(id_planeta);
    }
}

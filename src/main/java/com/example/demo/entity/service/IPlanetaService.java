package com.example.demo.entity.service;

import java.util.List;

import com.example.demo.entity.models.Planeta;

public interface IPlanetaService {
    List<Planeta>listarPlaneta();
    void guardarPlaneta(Planeta planeta);
    Planeta buscarPlanetaPorId(Long id_planeta);
    void eliminarPlaneta(Long id_planeta);
}

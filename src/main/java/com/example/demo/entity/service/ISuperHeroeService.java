package com.example.demo.entity.service;

import java.util.List;

import com.example.demo.entity.models.SuperHeroe;

public interface ISuperHeroeService {
    List<SuperHeroe>listarSuperhH();
    void guardarSuperH(SuperHeroe superHeroe);
    SuperHeroe buscarSuperHPorId(Long id_superheroe);
    void eliminarSuperH(Long id_superheroe);
}

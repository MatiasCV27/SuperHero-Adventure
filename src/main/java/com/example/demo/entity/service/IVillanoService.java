package com.example.demo.entity.service;

import java.util.List;

import com.example.demo.entity.models.Villano;

public interface IVillanoService {
    List<Villano>listarVillano();
    void guardarVillano(Villano villano);
    Villano buscarVillanoPorId(Long id_villano);
    void eliminarVillano(Long id_villano);
}

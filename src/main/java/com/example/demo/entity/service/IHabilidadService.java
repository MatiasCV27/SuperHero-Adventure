package com.example.demo.entity.service;

import java.util.List;

import com.example.demo.entity.models.Habilidad;

public interface IHabilidadService {
    List<Habilidad>listarHabilidad();
    void guardarHabilidad(Habilidad habilidad);
    Habilidad buscarHabilidadPorId(Long id_habilidad);
    void eliminarHabilidad(Long id_habilidad);
}

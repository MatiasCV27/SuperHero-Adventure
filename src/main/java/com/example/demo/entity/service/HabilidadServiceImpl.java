package com.example.demo.entity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.models.Habilidad;
import com.example.demo.entity.repository.HabilidadRepository;

@Service
public class HabilidadServiceImpl implements IHabilidadService{
    @Autowired
    private HabilidadRepository habilidadRepository;

    @Override
    public List<Habilidad>listarHabilidad() {
        return (List<Habilidad>) habilidadRepository.findAll();
    }
    @Override
    public void guardarHabilidad(Habilidad habilidad) {
        habilidadRepository.save(habilidad);
    }
    @Override
    public Habilidad buscarHabilidadPorId(Long id_habilidad) {
        return habilidadRepository.findById(id_habilidad).orElse(null);
    }
    @Override
    public void eliminarHabilidad(Long id_habilidad) {
        habilidadRepository.deleteById(id_habilidad);
    }
}

package com.example.demo.entity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.models.Villano;
import com.example.demo.entity.repository.VillanoRepository;

@Service
public class VillanosServiceImpl implements IVillanoService {
    @Autowired
    private VillanoRepository villanoRepository;

    @Override
    public List<Villano>listarVillano() {
        return (List<Villano>) villanoRepository.findAll();
    }
    @Override
    public void guardarVillano(Villano villano) {
        villanoRepository.save(villano);
    }
    @Override
    public Villano buscarVillanoPorId(Long id_villano) {
        return villanoRepository.findById(id_villano).orElse(null);
    }
    @Override
    public void eliminarVillano(Long id_villano) {
        villanoRepository.deleteById(id_villano);
    }
}

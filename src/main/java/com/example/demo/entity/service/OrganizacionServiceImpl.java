package com.example.demo.entity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.models.Organizacion;
import com.example.demo.entity.repository.OrganizacionRepository;

@Service
public class OrganizacionServiceImpl implements IOrganizacionService {
    @Autowired
    private OrganizacionRepository organizacionRepository;

    @Override
    public List<Organizacion>listarOrganizacion() {
        return (List<Organizacion>) organizacionRepository.findAll();
    }  
    @Override
    public void guardarOrganizacion(Organizacion organizacion) {
        organizacionRepository.save(organizacion);
    }
    @Override
    public Organizacion buscarOrganizacionPorId(Long id_organizacion) {
        return organizacionRepository.findById(id_organizacion).orElse(null);
    }
    @Override
    public void eliminarOrganizacion(Long id_organizacion) {
        organizacionRepository.deleteById(id_organizacion);
    }
}

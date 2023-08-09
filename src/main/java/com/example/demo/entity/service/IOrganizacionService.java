package com.example.demo.entity.service;

import java.util.List;

import com.example.demo.entity.models.Organizacion;

public interface IOrganizacionService {
    List<Organizacion>listarOrganizacion();   
    void guardarOrganizacion(Organizacion organizacion);
    Organizacion buscarOrganizacionPorId(Long id_organizacion);
    void eliminarOrganizacion(Long id_organizacion);
}

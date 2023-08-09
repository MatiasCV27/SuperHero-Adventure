package com.example.demo.entity.service;

import java.util.List;

import com.example.demo.entity.models.Roles;

public interface IRolesService {
    List<Roles>listarRoles();
    void guardarRoles(Roles roles);
    Roles buscarRolesPorId(Long id);
    void eliminarRoles(Long id);
}

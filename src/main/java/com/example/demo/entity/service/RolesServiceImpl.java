package com.example.demo.entity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.models.Roles;
import com.example.demo.entity.repository.RolesRepository;

@Service
public class RolesServiceImpl implements IRolesService {

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public List<Roles> listarRoles() {
        return (List<Roles>) rolesRepository.findAll();
    }
    @Override
    public void guardarRoles(Roles roles) {
        rolesRepository.save(roles);
    }
    @Override
    public Roles buscarRolesPorId(Long id) {
        return rolesRepository.findById(id).orElse(null);
    }
    @Override
    public void eliminarRoles(Long id) {
        rolesRepository.deleteById(id);
    }
    
}

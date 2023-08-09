package com.example.demo.entity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.models.Usuarios;
import com.example.demo.entity.repository.UsuariosRepository;

@Service
public class UsuariosServiceImpl implements IUsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    public List<Usuarios>listarUsuarios() {
        return (List<Usuarios>) usuariosRepository.findAll();
    }
    @Override
    public void guardarUsuarios(Usuarios usuarios) {
        usuariosRepository.save(usuarios);
    }
    @Override
    public Usuarios buscarUsuariosPorId(Long id) {
        return usuariosRepository.findById(id).orElse(null);
    }
    @Override
    public void eliminarUsuarios(Long id){
        usuariosRepository.deleteById(id);
    }
    
}

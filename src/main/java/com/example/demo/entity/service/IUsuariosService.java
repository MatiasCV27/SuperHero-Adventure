package com.example.demo.entity.service;

import java.util.List;

import com.example.demo.entity.models.Usuarios;

public interface IUsuariosService {
    List<Usuarios>listarUsuarios();
    void guardarUsuarios(Usuarios usuarios);
    Usuarios buscarUsuariosPorId(Long id);
    void eliminarUsuarios(Long id);
}

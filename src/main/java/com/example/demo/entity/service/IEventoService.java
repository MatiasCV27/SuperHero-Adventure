package com.example.demo.entity.service;

import java.util.List;

import com.example.demo.entity.models.Evento;

public interface IEventoService {
    List<Evento>listarEvento();
    void guardarEvento(Evento evento);
    Evento buscarEventoPorId(Long id_evento);
    void eliminarEvento(Long id_evento);
}

package com.example.demo.entity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.models.Evento;
import com.example.demo.entity.repository.EventoRepository;

@Service
public class EventoServiceImpl implements IEventoService {
    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public List<Evento>listarEvento() {
        return (List<Evento>) eventoRepository.findAll();
    }
    @Override
    public void guardarEvento(Evento evento) {
        eventoRepository.save(evento);
    }
    @Override
    public Evento buscarEventoPorId(Long id_evento) {
        return eventoRepository.findById(id_evento).orElse(null);
    }
    @Override
    public void eliminarEvento(Long id_evento) {
        eventoRepository.deleteById(id_evento);
    }
}

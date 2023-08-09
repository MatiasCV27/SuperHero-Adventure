package com.example.demo.entity.repository;

import org.springframework.data.repository.CrudRepository;
// import org.springframework.stereotype.Repository;

import com.example.demo.entity.models.Evento;

// @Repository
public interface EventoRepository extends CrudRepository<Evento, Long>  {
    
}

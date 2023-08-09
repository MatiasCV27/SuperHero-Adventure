package com.example.demo.entity.repository;

import org.springframework.data.repository.CrudRepository;
// import org.springframework.stereotype.Repository;

import com.example.demo.entity.models.Habilidad;

// @Repository
public interface HabilidadRepository  extends CrudRepository<Habilidad, Long> {
    
}

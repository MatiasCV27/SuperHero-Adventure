package com.example.demo.entity.repository;

import org.springframework.data.repository.CrudRepository;
// import org.springframework.stereotype.Repository;

import com.example.demo.entity.models.Villano;

// @Repository
public interface VillanoRepository extends CrudRepository<Villano, Long>{
    
}

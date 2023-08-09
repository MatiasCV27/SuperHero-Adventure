package com.example.demo.entity.repository;

import org.springframework.data.repository.CrudRepository;
// import org.springframework.stereotype.Repository;

import com.example.demo.entity.models.SuperHeroe;

// @Repository
public interface SuperHeroeRepository extends CrudRepository<SuperHeroe, Long> {
    
}

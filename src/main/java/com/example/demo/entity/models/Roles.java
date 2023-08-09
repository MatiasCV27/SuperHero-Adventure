package com.example.demo.entity.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Roles {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String rol;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuarios usuario;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public Usuarios getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public String toString() {
        return "Roles [id=" + id + ", rol=" + rol + ", usuario=" + usuario + "]";
    }
}

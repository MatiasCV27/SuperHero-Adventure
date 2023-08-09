package com.example.demo.entity.models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "habilidad")
public class Habilidad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_habilidad;

    @NotEmpty
    private String nombre_hab;
    
    @NotEmpty
    private String desc_hab;

    @NotEmpty
    private String categoria_hab;

    @NotEmpty
    private String alcance_hab;

    @NotEmpty
    private String dificultad_hab;

    @Lob
    private String imagen_hab;

    @ManyToMany(mappedBy = "habilidad")
    private List<SuperHeroe> superHeroe;
    
    public Long getId_habilidad() {
        return id_habilidad;
    }
    public void setId_habilidad(Long id_habilidad) {
        this.id_habilidad = id_habilidad;
    }
    public String getNombre_hab() {
        return nombre_hab;
    }
    public void setNombre_hab(String nombre_hab) {
        this.nombre_hab = nombre_hab;
    }
    public String getDesc_hab() {
        return desc_hab;
    }
    public void setDesc_hab(String desc_hab) {
        this.desc_hab = desc_hab;
    }
    public String getCategoria_hab() {
        return categoria_hab;
    }
    public void setCategoria_hab(String categoria_hab) {
        this.categoria_hab = categoria_hab;
    }
    public String getAlcance_hab() {
        return alcance_hab;
    }
    public void setAlcance_hab(String alcance_hab) {
        this.alcance_hab = alcance_hab;
    }
    public String getDificultad_hab() {
        return dificultad_hab;
    }
    public void setDificultad_hab(String dificultad_hab) {
        this.dificultad_hab = dificultad_hab;
    }
    public String getImagen_hab() {
        return imagen_hab;
    }
    public void setImagen_hab(String imagen_hab) {
        this.imagen_hab = imagen_hab;
    }
    public List<SuperHeroe> getSuperHeroe() {
        return superHeroe;
    }
    public void setSuperHeroe(List<SuperHeroe> superHeroe) {
        this.superHeroe = superHeroe;
    }

    @Override
    public String toString() {
        return "Habilidad [id_habilidad=" + id_habilidad + ", nombre_hab=" + nombre_hab + ", desc_hab=" + desc_hab
                + ", categoria_hab=" + categoria_hab + ", alcance_hab=" + alcance_hab + ", dificultad_hab="
                + dificultad_hab + ", imagen_hab=" + imagen_hab + ", superHeroe=" + superHeroe + "]";
    }
}

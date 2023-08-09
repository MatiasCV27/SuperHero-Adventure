package com.example.demo.entity.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "evento")
public class Evento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_evento;

    @NotEmpty
    private String nombre_eve;

    @NotEmpty
    private String desc_eve;

    @NotEmpty
    private String lugar_eve;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_eve;
    
    @Lob
    private String imagen_eve; 

    @ManyToMany(mappedBy = "evento")
    private List<SuperHeroe> superHeroe;
    
    public Long getId_evento() {
        return id_evento;
    }
    public void setId_evento(Long id_evento) {
        this.id_evento = id_evento;
    }
    public String getNombre_eve() {
        return nombre_eve;
    }
    public void setNombre_eve(String nombre_eve) {
        this.nombre_eve = nombre_eve;
    }
    public String getDesc_eve() {
        return desc_eve;
    }
    public void setDesc_eve(String desc_eve) {
        this.desc_eve = desc_eve;
    }
    public String getLugar_eve() {
        return lugar_eve;
    }
    public void setLugar_eve(String lugar_eve) {
        this.lugar_eve = lugar_eve;
    }
    public Date getFecha_eve() {
        return fecha_eve;
    }
    public void setFecha_eve(Date fecha_eve) {
        this.fecha_eve = fecha_eve;
    }
    public String getImagen_eve() {
        return imagen_eve;
    }
    public void setImagen_eve(String imagen_eve) {
        this.imagen_eve = imagen_eve;
    }
    
    public List<SuperHeroe> getSuperHeroe() {
        return superHeroe;
    }
    public void setSuperHeroe(List<SuperHeroe> superHeroe) {
        this.superHeroe = superHeroe;
    }

    @Override
    public String toString() {
        return "Evento [id_evento=" + id_evento + ", nombre_eve=" + nombre_eve + ", desc_eve=" + desc_eve
                + ", lugar_eve=" + lugar_eve + ", fecha_eve=" + fecha_eve + ", imagen_eve=" + imagen_eve
                + ", superHeroe=" + superHeroe + "]";
    }
}
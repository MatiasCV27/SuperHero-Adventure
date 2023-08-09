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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "organizacion")
public class Organizacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_organizacion;
    
    @NotEmpty
    private String nombre_org;

    @NotEmpty
    private String tipo_org;

    @NotEmpty
    private String base_opera_org;

    @NotEmpty
    private String objetivos_org;

    @NotEmpty
    private String desc_org;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_creacion_org;

    @NotEmpty
    private String financiamiento_org;
    @Lob
    private String imagen_org;

    @OneToMany(mappedBy = "organizacion")
    private List<SuperHeroe> superHeroe;

    public List<SuperHeroe> getSuperHeroe() {
        return superHeroe;
    }
    public void setSuperHeroe(List<SuperHeroe> superHeroes) {
        this.superHeroe = superHeroes;
    }
    public Long getId_organizacion() {
        return id_organizacion;
    }
    public void setId_organizacion(Long id_organizacion) {
        this.id_organizacion = id_organizacion;
    }
    public String getNombre_org() {
        return nombre_org;
    }
    public void setNombre_org(String nombre_org) {
        this.nombre_org = nombre_org;
    }
    public String getTipo_org() {
        return tipo_org;
    }
    public void setTipo_org(String tipo_org) {
        this.tipo_org = tipo_org;
    }
    public String getBase_opera_org() {
        return base_opera_org;
    }
    public void setBase_opera_org(String base_opera_org) {
        this.base_opera_org = base_opera_org;
    }
    public String getObjetivos_org() {
        return objetivos_org;
    }
    public void setObjetivos_org(String objetivos_org) {
        this.objetivos_org = objetivos_org;
    }
    public String getDesc_org() {
        return desc_org;
    }
    public void setDesc_org(String desc_org) {
        this.desc_org = desc_org;
    }
    public Date getFecha_creacion_org() {
        return fecha_creacion_org;
    }
    public void setFecha_creacion_org(Date fecha_creacion_org) {
        this.fecha_creacion_org = fecha_creacion_org;
    }
    public String getFinanciamiento_org() {
        return financiamiento_org;
    }
    public void setFinanciamiento_org(String financiamiento_org) {
        this.financiamiento_org = financiamiento_org;
    }
    public String getImagen_org() {
        return imagen_org;
    }
    public void setImagen_org(String imagen_org) {
        this.imagen_org = imagen_org;
    }

    @Override
    public String toString() {
        return "Organizacion [id_organizacion=" + id_organizacion + ", nombre_org=" + nombre_org + ", tipo_org="
                + tipo_org + ", base_opera_org=" + base_opera_org + ", objetivos_org=" + objetivos_org + ", desc_org="
                + desc_org + ", fecha_creacion_org=" + fecha_creacion_org + ", financiamiento_org=" + financiamiento_org
                + ", imagen_org=" + imagen_org + ", superHeroe=" + superHeroe + "]";
    }
}

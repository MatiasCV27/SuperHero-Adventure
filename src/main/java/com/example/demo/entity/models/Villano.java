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
@Table(name = "villano")
public class Villano implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_villano;

    @NotEmpty
    private String nombre_vil;
    
    @NotEmpty
    private String desc_vil;

    @NotEmpty
    private String personalidad_vil;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_naci_vil;

    @NotNull
    private Integer edad_vil;

    @NotEmpty
    private String nivel_amenaza_vil;

    @NotEmpty
    private String inteligencia_vil;
    
    @Lob
    private String imagen_vil;

    @ManyToMany(mappedBy = "villano")
    private List<SuperHeroe> superHeroe;
    
    public Long getId_villano() {
        return id_villano;
    }
    public void setId_villano(Long id_villano) {
        this.id_villano = id_villano;
    }
    public String getNombre_vil() {
        return nombre_vil;
    }
    public void setNombre_vil(String nombre_vil) {
        this.nombre_vil = nombre_vil;
    }
    public String getDesc_vil() {
        return desc_vil;
    }
    public void setDesc_vil(String desc_vil) {
        this.desc_vil = desc_vil;
    }
    public String getPersonalidad_vil() {
        return personalidad_vil;
    }
    public void setPersonalidad_vil(String personalidad_vil) {
        this.personalidad_vil = personalidad_vil;
    }
    public Date getFecha_naci_vil() {
        return fecha_naci_vil;
    }
    public void setFecha_naci_vil(Date fecha_naci_vil) {
        this.fecha_naci_vil = fecha_naci_vil;
    }
    public Integer getEdad_vil() {
        return edad_vil;
    }
    public void setEdad_vil(Integer edad_vil) {
        this.edad_vil = edad_vil;
    }
    public String getNivel_amenaza_vil() {
        return nivel_amenaza_vil;
    }
    public void setNivel_amenaza_vil(String nivel_amenaza_vil) {
        this.nivel_amenaza_vil = nivel_amenaza_vil;
    }
    public String getInteligencia_vil() {
        return inteligencia_vil;
    }
    public void setInteligencia_vil(String inteligencia_vil) {
        this.inteligencia_vil = inteligencia_vil;
    }
    public String getImagen_vil() {
        return imagen_vil;
    }
    public void setImagen_vil(String imagen_vil) {
        this.imagen_vil = imagen_vil;
    }
    public List<SuperHeroe> getSuperHeroe() {
        return superHeroe;
    }
    public void setSuperHeroe(List<SuperHeroe> superHeroe) {
        this.superHeroe = superHeroe;
    }

    @Override
    public String toString() {
        return "Villano [id_villano=" + id_villano + ", nombre_vil=" + nombre_vil + ", desc_vil=" + desc_vil
                + ", personalidad_vil=" + personalidad_vil + ", fecha_naci_vil=" + fecha_naci_vil + ", edad_vil="
                + edad_vil + ", nivel_amenaza_vil=" + nivel_amenaza_vil + ", inteligencia_vil=" + inteligencia_vil
                + ", imagen_vil=" + imagen_vil + ", superHeroe=" + superHeroe + "]";
    }
}
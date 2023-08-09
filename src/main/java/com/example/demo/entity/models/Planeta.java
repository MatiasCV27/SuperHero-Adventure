package com.example.demo.entity.models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "planeta")
public class Planeta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_planeta;

    @NotEmpty
    private String nombre_pla;

    @NotEmpty
    private String tipo_pla;

    @NotEmpty
    private String desc_pla;

    @NotEmpty
    private String poder_pla;

    @NotEmpty
    private String tecnologia_pla;

    @NotNull
    @Min(value = 0)
    private Integer nro_lunas_pla;

    @Lob
    private String imagen_pla;

    @OneToMany(mappedBy = "planeta")
    private List<SuperHeroe> superHeroe;
    
    public Long getId_planeta() {
        return id_planeta;
    }
    public void setId_planeta(Long id_planeta) {
        this.id_planeta = id_planeta;
    }
    public String getNombre_pla() {
        return nombre_pla;
    }
    public void setNombre_pla(String nombre_pla) {
        this.nombre_pla = nombre_pla;
    }
    public String getTipo_pla() {
        return tipo_pla;
    }
    public void setTipo_pla(String tipo_pla) {
        this.tipo_pla = tipo_pla;
    }
    public String getDesc_pla() {
        return desc_pla;
    }
    public void setDesc_pla(String desc_pla) {
        this.desc_pla = desc_pla;
    }
    public String getPoder_pla() {
        return poder_pla;
    }
    public void setPoder_pla(String poder_pla) {
        this.poder_pla = poder_pla;
    }
    public String getTecnologia_pla() {
        return tecnologia_pla;
    }
    public void setTecnologia_pla(String tecnologia_pla) {
        this.tecnologia_pla = tecnologia_pla;
    }
    public Integer getNro_lunas_pla() {
        return nro_lunas_pla;
    }
    public void setNro_lunas_pla(Integer nro_lunas_pla) {
        this.nro_lunas_pla = nro_lunas_pla;
    }
    public String getImagen_pla() {
        return imagen_pla;
    }
    public void setImagen_pla(String imagen_pla) {
        this.imagen_pla = imagen_pla;
    }
    public List<SuperHeroe> getSuperHeroe() {
        return superHeroe;
    }
    public void setSuperHeroe(List<SuperHeroe> superHeroe) {
        this.superHeroe = superHeroe;
    }

    @Override
    public String toString() {
        return "Planeta [id_planeta=" + id_planeta + ", nombre_pla=" + nombre_pla + ", tipo_pla=" + tipo_pla
                + ", desc_pla=" + desc_pla + ", poder_pla=" + poder_pla + ", tecnologia_pla=" + tecnologia_pla
                + ", nro_lunas_pla=" + nro_lunas_pla + ", imagen_pla=" + imagen_pla + ", superHeroe=" + superHeroe
                + "]";
    }
}

package com.example.demo.entity.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "superheroe")
public class SuperHeroe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_superheroe;

    //@NotEmpty
    private String nombre_sh;
    
    //@NotEmpty
    private String alias_sh;

    //@NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_naci_sh;

    //@NotNull
    private Integer edad_sh;  
    
    //@NotEmpty
    private String genero_sh;

    //@NotEmpty
    private String nacionalidad_sh;

    //@NotEmpty
    private String debilidad_sh;

    //@NotEmpty
    private String desc_sh;

    @Lob
    private String imagen_sh;

    @ManyToOne
    @JoinColumn(name = "id_organizacion")
    private Organizacion organizacion;

    @ManyToOne
    @JoinColumn(name = "id_planeta")
    private Planeta planeta;  

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "SuperHeroe_Villano",
        joinColumns = {@JoinColumn(name = "id_superheroe")},
        inverseJoinColumns = {@JoinColumn(name = "id_villano")})
    private List<Villano> villano = new ArrayList<Villano>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "SuperHeroe_Habilidad",
        joinColumns = {@JoinColumn(name = "id_superheroe")},
        inverseJoinColumns = {@JoinColumn(name = "id_habilidad")})
    private List<Habilidad> habilidad = new ArrayList<Habilidad>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "SuperHeroe_Evento",
        joinColumns = {@JoinColumn(name = "id_superheroe")},
        inverseJoinColumns = {@JoinColumn(name = "id_evento")})
    private List<Evento> evento = new ArrayList<Evento>();

    public Long getId_superheroe() {
        return id_superheroe;
    }

    public void setId_superheroe(Long id_superheroe) {
        this.id_superheroe = id_superheroe;
    }

    public String getNombre_sh() {
        return nombre_sh;
    }

    public void setNombre_sh(String nombre_sh) {
        this.nombre_sh = nombre_sh;
    }

    public String getAlias_sh() {
        return alias_sh;
    }

    public void setAlias_sh(String alias_sh) {
        this.alias_sh = alias_sh;
    }

    public Date getFecha_naci_sh() {
        return fecha_naci_sh;
    }

    public void setFecha_naci_sh(Date fecha_naci_sh) {
        this.fecha_naci_sh = fecha_naci_sh;
    }

    public Integer getEdad_sh() {
        return edad_sh;
    }

    public void setEdad_sh(Integer edad_sh) {
        this.edad_sh = edad_sh;
    }

    public String getGenero_sh() {
        return genero_sh;
    }

    public void setGenero_sh(String genero_sh) {
        this.genero_sh = genero_sh;
    }

    public String getNacionalidad_sh() {
        return nacionalidad_sh;
    }

    public void setNacionalidad_sh(String nacionalidad_sh) {
        this.nacionalidad_sh = nacionalidad_sh;
    }

    public String getDebilidad_sh() {
        return debilidad_sh;
    }

    public void setDebilidad_sh(String debilidad_sh) {
        this.debilidad_sh = debilidad_sh;
    }

    public String getDesc_sh() {
        return desc_sh;
    }

    public void setDesc_sh(String desc_sh) {
        this.desc_sh = desc_sh;
    }

    public String getImagen_sh() {
        return imagen_sh;
    }

    public void setImagen_sh(String imagen_sh) {
        this.imagen_sh = imagen_sh;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public Planeta getPlaneta() {
        return planeta;
    }

    public void setPlaneta(Planeta planeta) {
        this.planeta = planeta;
    }

    public List<Villano> getVillano() {
        return villano;
    }

    public void setVillano(List<Villano> villano) {
        this.villano = villano;
    }

    public List<Habilidad> getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(List<Habilidad> habilidad) {
        this.habilidad = habilidad;
    }

    public List<Evento> getEvento() {
        return evento;
    }

    public void setEvento(List<Evento> evento) {
        this.evento = evento;
    }

    @Override
    public String toString() {
        return "SuperHeroe [id_superheroe=" + id_superheroe + ", nombre_sh=" + nombre_sh + ", alias_sh=" + alias_sh
                + ", fecha_naci_sh=" + fecha_naci_sh + ", edad_sh=" + edad_sh + ", genero_sh=" + genero_sh
                + ", nacionalidad_sh=" + nacionalidad_sh + ", debilidad_sh=" + debilidad_sh + ", desc_sh=" + desc_sh
                + ", imagen_sh=" + imagen_sh + ", organizacion=" + organizacion + ", planeta=" + planeta + ", villano="
                + villano + ", habilidad=" + habilidad + ", evento=" + evento + "]";
    }
}

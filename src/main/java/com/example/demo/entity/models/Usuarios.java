package com.example.demo.entity.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Short enabled;

    @OneToMany(mappedBy = "usuario")
    private List<Roles> roles;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Short getEnabled() {
        return enabled;
    }
    public void setEnabled(Short enabled) {
        this.enabled = enabled;
    }
    public List<Roles> getRoles() {
        return roles;
    }
    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Usuarios [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
                + ", roles=" + roles + "]";
    }
}
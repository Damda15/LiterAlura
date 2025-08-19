package com.literalura.app.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer añoNacimiento;
    private Integer añoFallecimiento;

    @ManyToMany(mappedBy = "autores", fetch = FetchType.LAZY)
    private List<Libro> libros;

    // Constructores
    public Autor() {}  // ← Constructor vacío (OBLIGATORIO para JPA)

    public Autor(String nombre, Integer añoNacimiento, Integer añoFallecimiento) {
        this.nombre = nombre;
        this.añoNacimiento = añoNacimiento;
        this.añoFallecimiento = añoFallecimiento;
    }

    // Getters y Setters (OBLIGATORIOS para JPA)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getAñoNacimiento() { return añoNacimiento; }
    public void setAñoNacimiento(Integer añoNacimiento) { this.añoNacimiento = añoNacimiento; }

    public Integer getAñoFallecimiento() { return añoFallecimiento; }
    public void setAñoFallecimiento(Integer añoFallecimiento) { this.añoFallecimiento = añoFallecimiento; }

    public List<Libro> getLibros() { return libros; }
    public void setLibros(List<Libro> libros) { this.libros = libros; }
}
package com.aluracursos.Rovero.LiterAlura.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "autores")  // Nombre explícito de tabla
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)  // Asegura que el nombre sea obligatorio
    private String nombre;

    @Column(name = "anio_nacimiento", nullable = false)  // Mejor nombre de columna
    private Integer anioNacimiento;  // Cambiado a Integer para consistencia

    @Column(name = "anio_fallecimiento")  // Permite null para autores vivos
    private Integer anioFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Libro> libros;

    // Constructores
    public Autor() {}  // Constructor vacío necesario para JPA

    public Autor(String nombre, Integer anioNacimiento, Integer anioFallecimiento) {
        this.nombre = nombre;
        this.anioNacimiento = anioNacimiento;
        this.anioFallecimiento = anioFallecimiento;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Integer getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(Integer anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    // Método toString útil para debugging
    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", anioNacimiento=" + anioNacimiento +
                ", anioFallecimiento=" + anioFallecimiento +
                '}';
    }

    // Método helper para verificar si el autor estaba vivo en un año dado
    public boolean estabaVivoEn(int anio) {
        return anioFallecimiento == null || anio <= anioFallecimiento;
    }
}
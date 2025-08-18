package com.aluracursos.Rovero.LiterAlura.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    @ToString.Exclude
    private Autor autor;

    private String idioma;

    @Column(unique = true)
    private String isbn;

    // Constructor vacío necesario para JPA
    public Libro() {}

    // Constructor con parámetros principales
    public Libro(String titulo, Autor autor, String idioma) {
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma != null ? idioma.toUpperCase() : null;
    }

    // Método helper para obtener nombre del autor
    public String getNombreAutor() {
        return autor != null ? autor.getNombre() : "Desconocido";
    }

    // Método helper para obtener años de vida del autor
    public String getVidaAutor() {
        if (autor == null) return "";
        return "(" + autor.getAnioNacimiento() +
                (autor.getAnioFallecimiento() != null ?
                        "-" + autor.getAnioFallecimiento() : "") + ")";
    }
}
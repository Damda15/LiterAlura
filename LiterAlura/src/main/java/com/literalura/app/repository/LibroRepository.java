package com.literalura.app.repository;

import com.literalura.app.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Buscar libros por título (ignore mayúsculas/minúsculas)
    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    // Buscar libros por idioma
    List<Libro> findByIdioma(String idioma);

    // Buscar libro por título exacto
    Optional<Libro> findByTitulo(String titulo);

    // Todos los libros ordenados por título
    List<Libro> findAllByOrderByTituloAsc();
}
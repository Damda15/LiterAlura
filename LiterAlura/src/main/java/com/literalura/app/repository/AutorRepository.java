package com.literalura.app.repository;

import com.literalura.app.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Buscar autores por nombre (ignore mayúsculas/minúsculas)
    List<Autor> findByNombreContainingIgnoreCase(String nombre);

    // Autores vivos en un año determinado
    @Query("SELECT a FROM Autor a WHERE a.añoNacimiento <= :año AND (a.añoFallecimiento >= :año OR a.añoFallecimiento IS NULL)")
    List<Autor> findAutoresVivosEnAño(@Param("año") Integer año);

    // Todos los autores ordenados por nombre
    List<Autor> findAllByOrderByNombreAsc();

    // Buscar autor por nombre exacto
    Optional<Autor> findByNombre(String nombre);
}
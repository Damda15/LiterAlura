package com.aluracursos.Rovero.LiterAlura.repository;

import com.aluracursos.Rovero.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a JOIN FETCH a.libros")
    List<Autor> findAllWithLibros();

    @Query("SELECT a FROM Autor a JOIN FETCH a.libros WHERE a.anioNacimiento <= :anio AND (a.anioFallecimiento >= :anio OR a.anioFallecimiento = 0)")
    List<Autor> findAutoresVivosEnAnio(int anio);

    // Añade este nuevo método
    List<Autor> findByAnioFallecimientoGreaterThan(int anio);
}
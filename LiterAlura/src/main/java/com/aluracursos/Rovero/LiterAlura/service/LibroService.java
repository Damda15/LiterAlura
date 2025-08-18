package com.aluracursos.Rovero.LiterAlura.service;

import com.aluracursos.Rovero.LiterAlura.model.Autor;
import com.aluracursos.Rovero.LiterAlura.model.Libro;
import com.aluracursos.Rovero.LiterAlura.repository.AutorRepository;
import com.aluracursos.Rovero.LiterAlura.repository.LibroRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final GutendexService gutendexService;

    public LibroService(LibroRepository libroRepository,
                        AutorRepository autorRepository,
                        GutendexService gutendexService) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.gutendexService = gutendexService;
    }

    @Transactional
    public Optional<Libro> buscarYGuardarLibro(String titulo) {
        try {
            if (titulo == null || titulo.isBlank()) {
                return Optional.empty();
            }

            Optional<Libro> libroOptional = gutendexService.buscarLibro(titulo.trim());

            if (libroOptional.isEmpty()) {
                return Optional.empty();
            }

            Libro libro = libroOptional.get();
            procesarAutor(libro);

            return Optional.of(libroRepository.save(libro));

        } catch (DataIntegrityViolationException e) {
            System.err.println("Error de integridad de datos al guardar el libro: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado al buscar y guardar libro: " + e.getMessage());
        }
        return Optional.empty();
    }

    private void procesarAutor(Libro libro) {
        if (libro.getAutor() == null || libro.getAutor().getNombre() == null) {
            return;
        }

        List<Autor> autores = autorRepository.findByNombreContainingIgnoreCase(libro.getAutor().getNombre());
        if (!autores.isEmpty()) {
            libro.setAutor(autores.get(0));
        } else {
            try {
                autorRepository.save(libro.getAutor());
            } catch (Exception e) {
                System.err.println("Error al guardar autor: " + e.getMessage());
            }
        }
    }

    @Transactional(readOnly = true)
    public List<Libro> listarLibros() {
        return libroRepository.findAllWithAutor();
    }

    @Transactional(readOnly = true)
    public List<Autor> listarAutores() {
        return autorRepository.findAllWithLibros();
    }

    @Transactional(readOnly = true)
    public List<Autor> listarAutoresVivosEn(int anio) {
        return autorRepository.findAutoresVivosEnAnio(anio);
    }

    @Transactional(readOnly = true)
    public List<Libro> listarLibrosPorIdioma(String idioma) {
        if (idioma == null || idioma.isBlank()) {
            return List.of();
        }
        return libroRepository.findByIdiomaContainingIgnoreCase(idioma.trim().toUpperCase());
    }

    @Transactional(readOnly = true)
    public List<Libro> buscarPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            return List.of();
        }
        return libroRepository.findByTituloContainingIgnoreCase(titulo.trim());
    }
}
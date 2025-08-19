package com.literalura.app.service;

import com.literalura.app.model.Libro;
import com.literalura.app.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    // 1. Buscar libros por título
    public List<Libro> buscarLibrosPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    // 2. Listar todos los libros registrados
    public List<Libro> listarTodosLosLibros() {
        return libroRepository.findAllByOrderByTituloAsc();
    }

    // 3. Listar libros por idioma
    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }

    // 4. Guardar un libro
    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    // 5. Buscar libro por título exacto
    public Optional<Libro> buscarPorTituloExacto(String titulo) {
        return libroRepository.findByTitulo(titulo);
    }
}
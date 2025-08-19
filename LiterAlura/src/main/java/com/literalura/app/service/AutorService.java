package com.literalura.app.service;

import com.literalura.app.model.Autor;
import com.literalura.app.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    // 1. Listar todos los autores registrados
    public List<Autor> listarTodosLosAutores() {
        return autorRepository.findAllByOrderByNombreAsc();
    }

    // 2. Listar autores vivos en un año determinado
    public List<Autor> listarAutoresVivosEnAño(Integer año) {
        return autorRepository.findAutoresVivosEnAño(año);
    }

    // 3. Buscar autores por nombre
    public List<Autor> buscarAutoresPorNombre(String nombre) {
        return autorRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // 4. Guardar un autor
    public Autor guardarAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    // 5. Buscar autor por nombre exacto
    public Optional<Autor> buscarPorNombreExacto(String nombre) {
        return autorRepository.findByNombre(nombre);
    }
}
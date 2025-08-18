package com.aluracursos.Rovero.LiterAlura.service;


import com.aluracursos.Rovero.LiterAlura.model.Autor;
import com.aluracursos.Rovero.LiterAlura.repository.AutorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> listarVivosEn(int anio) {
        return autorRepository.findByAnioFallecimientoGreaterThan(anio);
    }

    public void guardar(Autor autor) {
        autorRepository.save(autor);
    }
}


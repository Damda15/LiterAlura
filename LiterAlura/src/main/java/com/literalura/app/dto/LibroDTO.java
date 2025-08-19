package com.literalura.app.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LibroDTO {
    private String title;

    @JsonAlias("authors")
    private List<AutorDTO> autores;

    @JsonAlias("languages")
    private List<String> idiomas;

    @JsonAlias("download_count")
    private Integer descargas;

    // Constructores
    public LibroDTO() {}

    public LibroDTO(String title, List<AutorDTO> autores, List<String> idiomas, Integer descargas) {
        this.title = title;
        this.autores = autores;
        this.idiomas = idiomas;
        this.descargas = descargas;
    }

    // Getters y Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<AutorDTO> getAutores() { return autores; }
    public void setAutores(List<AutorDTO> autores) { this.autores = autores; }

    public List<String> getIdiomas() { return idiomas; }
    public void setIdiomas(List<String> idiomas) { this.idiomas = idiomas; }

    public Integer getDescargas() { return descargas; }
    public void setDescargas(Integer descargas) { this.descargas = descargas; }

    // Método útil para obtener el primer idioma
    public String getPrimerIdioma() {
        return idiomas != null && !idiomas.isEmpty() ? idiomas.get(0) : "en";
    }

    @Override
    public String toString() {
        return "LibroDTO{" +
                "title='" + title + '\'' +
                ", autores=" + autores +
                ", idiomas=" + idiomas +
                ", descargas=" + descargas +
                '}';
    }
}
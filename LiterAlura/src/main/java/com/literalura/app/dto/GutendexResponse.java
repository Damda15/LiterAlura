package com.literalura.app.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexResponse {

    @JsonAlias("count")
    private Integer total;

    @JsonAlias("next")
    private String siguientePagina;

    @JsonAlias("previous")
    private String paginaAnterior;

    @JsonAlias("results")
    private List<LibroDTO> resultados;

    // Constructores
    public GutendexResponse() {}

    public GutendexResponse(Integer total, String siguientePagina, String paginaAnterior, List<LibroDTO> resultados) {
        this.total = total;
        this.siguientePagina = siguientePagina;
        this.paginaAnterior = paginaAnterior;
        this.resultados = resultados;
    }

    // Getters y Setters
    public Integer getTotal() { return total; }
    public void setTotal(Integer total) { this.total = total; }

    public String getSiguientePagina() { return siguientePagina; }
    public void setSiguientePagina(String siguientePagina) { this.siguientePagina = siguientePagina; }

    public String getPaginaAnterior() { return paginaAnterior; }
    public void setPaginaAnterior(String paginaAnterior) { this.paginaAnterior = paginaAnterior; }

    public List<LibroDTO> getResultados() { return resultados; }
    public void setResultados(List<LibroDTO> resultados) { this.resultados = resultados; }

    @Override
    public String toString() {
        return "GutendexResponse{" +
                "total=" + total +
                ", siguientePagina='" + siguientePagina + '\'' +
                ", paginaAnterior='" + paginaAnterior + '\'' +
                ", resultados=" + resultados +
                '}';
    }
}
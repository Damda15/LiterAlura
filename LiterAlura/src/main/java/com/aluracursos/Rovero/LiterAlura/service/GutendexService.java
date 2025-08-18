package com.aluracursos.Rovero.LiterAlura.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GutendexService {
    private static final String BASE_URL = "https://gutendex.com/books/";
    private final RestTemplate restTemplate;

    public GutendexService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Búsqueda por título (existente)
    public String buscarPorTitulo(String titulo) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("search", titulo.replace(" ", "%20"))
                .toUriString();
        return realizarPeticion(url);
    }

    // Nuevos métodos para otros endpoints
    public String buscarPorAutor(String autor) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("search", "author:" + autor.replace(" ", "%20"))
                .toUriString();
        return realizarPeticion(url);
    }

    public String listarPopulares() {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("sort", "popular")
                .toUriString();
        return realizarPeticion(url);
    }

    public String buscarPorIdioma(String codigoIdioma) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("languages", codigoIdioma.toLowerCase())
                .toUriString();
        return realizarPeticion(url);
    }

    private String realizarPeticion(String url) {
        try {
            System.out.println("🔗 Solicitando: " + url); // Debug
            String respuesta = restTemplate.getForObject(url, String.class);
            System.out.println("✅ Respuesta recibida"); // Debug
            return respuesta;
        } catch (Exception e) {
            System.err.println("❌ Error en la petición: " + e.getMessage());
            return null;
        }
    }
}
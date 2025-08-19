package com.literalura.app.service;

import com.literalura.app.dto.GutendexResponse;
import com.literalura.app.dto.LibroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;
import java.util.Optional;

@Service
public class GutendexService {

    private static final String GUTENDEX_API_URL = "https://gutendex.com/books/";
    private final RestTemplate restTemplate;

    // Constructor con inyección de dependencias
    @Autowired
    public GutendexService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Buscar libros por título
    public Optional<LibroDTO> buscarLibroPorTitulo(String titulo) {
        String url = UriComponentsBuilder.fromHttpUrl(GUTENDEX_API_URL)
                .queryParam("search", titulo.replace(" ", "%20"))
                .toUriString();

        System.out.println("🔍 Buscando en Gutendex: " + url);

        try {
            GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);
            if (response != null && response.getResultados() != null && !response.getResultados().isEmpty()) {
                LibroDTO libroEncontrado = response.getResultados().get(0);
                System.out.println("✅ Libro encontrado: " + libroEncontrado.getTitle());
                return Optional.of(libroEncontrado);
            } else {
                System.out.println("❌ No se encontraron resultados para: " + titulo);
            }
        } catch (Exception e) {
            System.out.println("❌ Error al conectar con Gutendex: " + e.getMessage());
            System.out.println("💡 Verifica tu conexión a internet");
        }
        return Optional.empty();
    }

    // Obtener todos los libros (paginados)
    public List<LibroDTO> obtenerTodosLosLibros() {
        System.out.println("📚 Obteniendo todos los libros de Gutendex...");

        try {
            GutendexResponse response = restTemplate.getForObject(GUTENDEX_API_URL, GutendexResponse.class);
            if (response != null && response.getResultados() != null) {
                System.out.println("✅ Encontrados " + response.getResultados().size() + " libros");
                return response.getResultados();
            }
        } catch (Exception e) {
            System.out.println("❌ Error al obtener libros: " + e.getMessage());
        }
        return List.of();
    }

    // Buscar libros por idioma
    public List<LibroDTO> buscarLibrosPorIdioma(String idioma) {
        String url = UriComponentsBuilder.fromHttpUrl(GUTENDEX_API_URL)
                .queryParam("languages", idioma)
                .toUriString();

        System.out.println("🌍 Buscando libros en idioma: " + idioma);

        try {
            GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);
            if (response != null && response.getResultados() != null) {
                System.out.println("✅ Encontrados " + response.getResultados().size() + " libros en " + idioma);
                return response.getResultados();
            }
        } catch (Exception e) {
            System.out.println("❌ Error al buscar por idioma: " + e.getMessage());
        }
        return List.of();
    }

    // Obtener libro por ID
    public Optional<LibroDTO> obtenerLibroPorId(Long id) {
        String url = GUTENDEX_API_URL + id + "/";
        System.out.println("🔎 Buscando libro por ID: " + id);

        try {
            LibroDTO libro = restTemplate.getForObject(url, LibroDTO.class);
            if (libro != null) {
                System.out.println("✅ Libro encontrado: " + libro.getTitle());
                return Optional.of(libro);
            } else {
                System.out.println("❌ No se encontró libro con ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("❌ Error al obtener libro por ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    // Método adicional: Buscar múltiples libros por título
    public List<LibroDTO> buscarLibrosPorTituloMultiple(String titulo) {
        String url = UriComponentsBuilder.fromHttpUrl(GUTENDEX_API_URL)
                .queryParam("search", titulo.replace(" ", "%20"))
                .toUriString();

        System.out.println("🔍 Búsqueda múltiple: " + titulo);

        try {
            GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);
            if (response != null && response.getResultados() != null) {
                System.out.println("✅ Encontrados " + response.getResultados().size() + " resultados");
                return response.getResultados();
            }
        } catch (Exception e) {
            System.out.println("❌ Error en búsqueda múltiple: " + e.getMessage());
        }
        return List.of();
    }
}
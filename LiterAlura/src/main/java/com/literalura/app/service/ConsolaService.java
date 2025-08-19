package com.literalura.app.service;

import com.literalura.app.dto.LibroDTO;
import com.literalura.app.model.Libro;
import com.literalura.app.model.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Scanner;
import java.util.Optional;

@Service
public class ConsolaService {

    @Autowired
    private LibroService libroService;

    @Autowired
    private AutorService autorService;

    @Autowired
    private GutendexService gutendexService;

    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        while (true) {
            try {
                System.out.println("\n=== 📚 LITERALURA - Catálogo de Libros ===");
                System.out.println("1. 🔍 Buscar libros por título (en mi BD)");
                System.out.println("2. 📖 Listar libros registrados");
                System.out.println("3. 👥 Listar autores registrados");
                System.out.println("4. 🎂 Listar autores vivos en un año determinado");
                System.out.println("5. 🌍 Listar libros por idioma");
                System.out.println("6. ⬇️ Buscar y guardar libro de Gutendex");
                System.out.println("7. 🔎 Buscar en Gutendex (solo ver resultados)");
                System.out.println("0. 🚪 Salir");
                System.out.print("Seleccione una opción: ");

                if (scanner.hasNextInt()) {
                    int opcion = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    switch (opcion) {
                        case 1:
                            buscarLibrosPorTitulo();
                            break;
                        case 2:
                            listarLibrosRegistrados();
                            break;
                        case 3:
                            listarAutoresRegistrados();
                            break;
                        case 4:
                            listarAutoresVivosEnAño();
                            break;
                        case 5:
                            listarLibrosPorIdioma();
                            break;
                        case 6:
                            buscarYGuardarLibro();
                            break;
                        case 7:
                            buscarEnGutendex();
                            break;
                        case 0:
                            System.out.println("¡Hasta pronto! 👋");
                            return;
                        default:
                            System.out.println("❌ Opción no válida. Intente nuevamente.");
                    }
                } else {
                    System.out.println("❌ Por favor, ingrese un número válido.");
                    scanner.next(); // Limpiar entrada inválida
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
                scanner.nextLine(); // Limpiar buffer en caso de error
            }
        }
    }

    // NUEVO: Solo buscar en Gutendex y mostrar resultados
    private void buscarEnGutendex() {
        System.out.print("🔍 Ingrese título a buscar en Gutendex: ");
        String titulo = scanner.nextLine();

        Optional<LibroDTO> libroDTOOpt = gutendexService.buscarLibroPorTitulo(titulo);

        if (libroDTOOpt.isPresent()) {
            LibroDTO libroDTO = libroDTOOpt.get();
            System.out.println("\n✅ LIBRO ENCONTRADO EN GUTENDEX:");
            System.out.println("📖 Título: " + libroDTO.getTitle());
            System.out.println("🌍 Idioma: " + libroDTO.getPrimerIdioma());
            System.out.println("⬇️ Descargas: " + libroDTO.getDescargas());

            if (libroDTO.getAutores() != null && !libroDTO.getAutores().isEmpty()) {
                System.out.println("👥 Autores:");
                for (var autorDTO : libroDTO.getAutores()) {
                    System.out.println("   - " + autorDTO.getName() +
                            " (" + autorDTO.getBirthYear() + " - " + autorDTO.getDeathYear() + ")");
                }
            }

            System.out.print("\n¿Desea guardar este libro en la base de datos? (s/n): ");
            String respuesta = scanner.nextLine();

            if (respuesta.equalsIgnoreCase("s")) {
                Libro libro = convertirDTOaEntidad(libroDTO);
                libroService.guardarLibro(libro);
                System.out.println("💾 ¡Libro guardado exitosamente!");
            }

        } else {
            System.out.println("❌ No se encontró el libro en Gutendex");
        }
    }

    // Buscar y guardar libro (versión mejorada)
    private void buscarYGuardarLibro() {
        System.out.print("🔍 Ingrese título a buscar en Gutendex: ");
        String titulo = scanner.nextLine();

        Optional<LibroDTO> libroDTOOpt = gutendexService.buscarLibroPorTitulo(titulo);

        if (libroDTOOpt.isPresent()) {
            LibroDTO libroDTO = libroDTOOpt.get();

            // Mostrar información del libro encontrado
            System.out.println("\n✅ Libro encontrado: " + libroDTO.getTitle());
            System.out.println("🌍 Idioma: " + libroDTO.getPrimerIdioma());
            System.out.println("⬇️ Descargas: " + libroDTO.getDescargas());

            if (libroDTO.getAutores() != null && !libroDTO.getAutores().isEmpty()) {
                System.out.println("👥 Autores:");
                for (var autorDTO : libroDTO.getAutores()) {
                    System.out.println("   - " + autorDTO.getName());
                }
            }

            // Convertir DTO a entidad y guardar
            Libro libro = convertirDTOaEntidad(libroDTO);
            libroService.guardarLibro(libro);
            System.out.println("💾 ¡Libro guardado en la base de datos!");

        } else {
            System.out.println("❌ No se encontró el libro en Gutendex");
        }
    }

    // Método para convertir DTO a entidad (CORREGIDO)
    private Libro convertirDTOaEntidad(LibroDTO libroDTO) {
        Libro libro = new Libro();
        libro.setTitulo(libroDTO.getTitle());
        libro.setIdioma(libroDTO.getPrimerIdioma());
        libro.setDescargas(libroDTO.getDescargas());

        // Convertir autores DTO a entidades Autor
        if (libroDTO.getAutores() != null) {
            List<Autor> autores = libroDTO.getAutores().stream()
                    .map(autorDTO -> {
                        Autor autor = new Autor();
                        autor.setNombre(autorDTO.getName());

                        // CORREGIDO: Usar los nombres en inglés de la API
                        autor.setAñoNacimiento(autorDTO.getBirthYear());    // birthYear → añoNacimiento
                        autor.setAñoFallecimiento(autorDTO.getDeathYear()); // deathYear → añoFallecimiento

                        return autor;
                    })
                    .toList();
            libro.setAutores(autores);
        }

        return libro;
    }

    // Buscar libros en mi base de datos local
    private void buscarLibrosPorTitulo() {
        System.out.print("🔍 Ingrese título a buscar en mi base de datos: ");
        String titulo = scanner.nextLine();
        List<Libro> libros = libroService.buscarLibrosPorTitulo(titulo);

        if (libros.isEmpty()) {
            System.out.println("❌ No se encontraron libros con ese título en mi BD");
        } else {
            System.out.println("✅ Libros encontrados en mi BD:");
            for (Libro libro : libros) {
                System.out.println("📖 " + libro.getTitulo() +
                        " | 🌍 " + libro.getIdioma() +
                        " | ⬇️ " + libro.getDescargas() + " descargas");
            }
        }
    }

    // Listar libros registrados
    private void listarLibrosRegistrados() {
        List<Libro> libros = libroService.listarTodosLosLibros();
        if (libros.isEmpty()) {
            System.out.println("❌ No hay libros registrados en la base de datos");
        } else {
            System.out.println("📚 Libros registrados (" + libros.size() + "):");
            for (Libro libro : libros) {
                System.out.println("📖 " + libro.getTitulo() +
                        " | 🌍 " + libro.getIdioma() +
                        " | ⬇️ " + libro.getDescargas() + " descargas");
            }
        }
    }

    // Listar autores registrados
    private void listarAutoresRegistrados() {
        List<Autor> autores = autorService.listarTodosLosAutores();
        if (autores.isEmpty()) {
            System.out.println("❌ No hay autores registrados");
        } else {
            System.out.println("👥 Autores registrados (" + autores.size() + "):");
            for (Autor autor : autores) {
                String años = (autor.getAñoNacimiento() != null && autor.getAñoFallecimiento() != null) ?
                        " (" + autor.getAñoNacimiento() + " - " + autor.getAñoFallecimiento() + ")" :
                        " (Años desconocidos)";
                System.out.println("   - " + autor.getNombre() + años);
            }
        }
    }

    // Listar autores vivos en un año
    private void listarAutoresVivosEnAño() {
        System.out.print("🎂 Ingrese el año: ");
        int año = scanner.nextInt();
        scanner.nextLine();

        List<Autor> autores = autorService.listarAutoresVivosEnAño(año);
        if (autores.isEmpty()) {
            System.out.println("❌ No hay autores vivos en el año " + año);
        } else {
            System.out.println("👥 Autores vivos en " + año + ":");
            for (Autor autor : autores) {
                System.out.println("   - " + autor.getNombre());
            }
        }
    }

    // Listar libros por idioma
    private void listarLibrosPorIdioma() {
        System.out.print("🌍 Ingrese idioma (es/en/fr/pt/de/it): ");
        String idioma = scanner.nextLine();

        List<Libro> libros = libroService.listarLibrosPorIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("❌ No hay libros en el idioma " + idioma);
        } else {
            System.out.println("📚 Libros en " + idioma + " (" + libros.size() + "):");
            for (Libro libro : libros) {
                System.out.println("📖 " + libro.getTitulo() + " | ⬇️ " + libro.getDescargas() + " descargas");
            }
        }
    }
}
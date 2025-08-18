
package com.aluracursos.Rovero.LiterAlura;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.web.client.RestTemplate;

public class Gutendex {

    private static final String API_URL = "https://gutendex.com/books?search=";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RestTemplate restTemplate = new RestTemplate();

        while (true) {
            System.out.println("==== MENU ====");
            System.out.println("1 - Buscar libro por título");
            System.out.println("2 - Salir");
            System.out.print("Selecciona una opción: ");
            String opcion = scanner.nextLine();

            if (opcion.equals("1")) {
                System.out.print("Ingresa el título del libro: ");
                String titulo = scanner.nextLine();

                Map<?, ?> response = restTemplate.getForObject(API_URL + titulo, Map.class);

                if (response != null && response.containsKey("results")) {
                    List<Map<String, Object>> libros = (List<Map<String, Object>>) response.get("results");
                    if (libros.isEmpty()) {
                        System.out.println("No se encontraron libros con ese título.");
                    } else {
                        System.out.println("Libros encontrados:");
                        for (Map<String, Object> libro : libros) {
                            System.out.println("-------------------------");
                            System.out.println("Título: " + libro.get("title"));

                            List<Map<String, Object>> autores = (List<Map<String, Object>>) libro.get("authors");
                            if (autores.isEmpty()) {
                                System.out.println("Autor: Desconocido");
                            } else {
                                System.out.print("Autor(es): ");
                                for (Map<String, Object> autor : autores) {
                                    System.out.print(autor.get("name") + " ");
                                }
                                System.out.println();
                            }
                        }
                    }
                } else {
                    System.out.println("Error al consultar la API.");
                }

            } else if (opcion.equals("2")) {
                System.out.println("Saliendo...");
                break;
            } else {
                System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }

        scanner.close();
    }
}

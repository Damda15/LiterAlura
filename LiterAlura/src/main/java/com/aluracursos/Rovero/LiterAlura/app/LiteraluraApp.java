package com.aluracursos.Rovero.LiterAlura.app;

import com.aluracursos.Rovero.LiterAlura.service.LibroService;
import com.aluracursos.Rovero.LiterAlura.service.AutorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class LiteraluraApp implements CommandLineRunner {

    private final LibroService libroService;
    private final AutorService autorService;

    public LiteraluraApp(LibroService libroService, AutorService autorService) {
        this.libroService = libroService;
        this.autorService = autorService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("----- Menú Literalura -----");
            System.out.println("1) Buscar libros por título");
            System.out.println("2) Listar libros registrados");
            System.out.println("3) Listar autores registrados");
            System.out.println("4) Listar autores vivos en un año");
            System.out.println("5) Listar libros por idioma");
            System.out.println("6) Salir");
            System.out.print("Ingrese opción: ");

            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.println(libroService.buscarPorTitulo(titulo));
                    break;
                case 2:
                    System.out.println(libroService.listarLibros());
                    break;
                case 3:
                    System.out.println(autorService.listarAutores());
                    break;
                case 4:
                    System.out.print("Año: ");
                    int anio = Integer.parseInt(scanner.nextLine());
                    System.out.println(autorService.listarVivosEn(anio));
                    break;
                case 5:
                    System.out.print("Idioma: ");
                    String idioma = scanner.nextLine();
                    System.out.println(libroService.listarLibrosPorIdioma(idioma));
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
}

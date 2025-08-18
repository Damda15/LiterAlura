package com.aluracursos.Rovero.LiterAlura; // Asegúrate que coincida con tu paquete

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class DatabaseChecker implements CommandLineRunner {

    private final DataSource dataSource;

    public DatabaseChecker(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("✅ Conexión a PostgreSQL exitosa!");
            System.out.println("URL: " + conn.getMetaData().getURL());
            System.out.println("Usuario: " + conn.getMetaData().getUserName());
        } catch (Exception e) {
            System.err.println("❌ Error conectando a PostgreSQL:");
            e.printStackTrace();
        }
    }
}
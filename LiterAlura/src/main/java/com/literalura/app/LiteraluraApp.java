package com.literalura.app;

import com.literalura.app.service.ConsolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApp implements CommandLineRunner {

    @Autowired
    private ConsolaService consolaService;

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        consolaService.mostrarMenu();
    }
}
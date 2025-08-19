[![Java](https://img.shields.io/badge/Java-21-blue)](https://java.com)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen)](https://spring.io)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14-blue)](https://postgresql.org)
[![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)




# 📚 Literalura - Catálogo de Libros

Una aplicación de consola en Java con Spring Boot que permite explorar, buscar y gestionar un catálogo de libros consumiendo la API de Gutendex y almacenando datos en PostgreSQL.

## 🚀 Características

- **🔍 Búsqueda de libros** por título en la API Gutendex
- **💾 Persistencia** en base de datos PostgreSQL
- **📊 Gestión completa** de libros y autores
- **🌍 Filtrado** por idioma
- **👥 Consulta** de autores vivos por año
- **⚡ Interfaz de consola** interactiva

## 🛠️ Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3.5.4**
- **Spring Data JPA**
- **PostgreSQL**
- **Gutendex API** (https://gutendex.com/)
- **Maven**

## ⚙️ Configuración

### Requisitos Previos
- Java 21 JDK
- PostgreSQL 14+
- Maven 3.6+

### Base de Datos
Crear la base de datos en PostgreSQL:

```sql
CREATE DATABASE catalogo_libros;
CREATE USER literalura_user WITH PASSWORD 'tu_password';
GRANT ALL PRIVILEGES ON DATABASE catalogo_libros TO literalura_user;


🚀 Ejecución
Compilar y Ejecutar

mvn clean compile
mvn spring-boot:run


📋 Funcionalidades
Menú Principal
🔍 Buscar libros por título - En base de datos local

📖 Listar libros registrados - Todos los libros almacenados

👥 Listar autores registrados - Autores en la base de datos

🎂 Listar autores vivos en año - Autores vivos en año específico

🌍 Listar libros por idioma - Filtrar por idioma (es, en, fr, etc.)

⬇️ Buscar y guardar libro - Desde Gutendex API

🔎 Buscar en Gutendex - Solo consulta sin guardar


Ejemplos de Búsqueda
"Dracula"

"Don Quijote"

"Sherlock Holmes"

"Romeo and Juliet"


🌐 API Gutendex
La aplicación consume la API pública de Gutendex:

URL Base: https://gutendex.com/books/

Búsqueda: /books/?search={titulo}

Por idioma: /books/?languages={idioma}



📈 Futuras Mejoras
Interfaz web con Spring MVC

Autenticación de usuarios

Exportación a PDF/Excel

Estadísticas de lectura

Sistema de recomendaciones


🙏 Agradecimientos
Gutendex API por el acceso a libros del dominio público

Spring Boot por el framework

Alura Latam por el desafío

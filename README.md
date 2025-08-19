📚 Literalura - Catálogo de Libros

Una aplicación de consola en Java con Spring Boot que permite explorar, buscar y gestionar un catálogo de libros consumiendo la API de Gutendex y almacenando datos en PostgreSQL.


🚀 Características principales

🔍 Búsqueda de libros por título en la API Gutendex

💾 Persistencia de datos en base de datos PostgreSQL

📊 Gestión completa de libros y autores

🌍 Filtrado de libros por idioma

👥 Consulta de autores vivos en un año determinado

⚡ Interfaz de consola interactiva e intuitiva

🛠️ Tecnologías utilizadas

☕ Java 21

🍃 Spring Boot 3.5.4

🗄️ Spring Data JPA

🐘 PostgreSQL

📡 Gutendex API (https://gutendex.com/)

🔧 Maven

📦 Estructura del proyecto
src/main/java/com/literalura/app/
├── config/       # Configuración (RestTemplate, DB, etc.)
├── model/        # Entidades (Libro, Autor)
├── repository/   # Repositorios JPA
├── service/      # Lógica de negocio
├── dto/          # Objetos de transferencia (DTOs)
└── LiteraluraApp.java   # Clase principal

⚙️ Configuración
🔑 Requisitos previos

Java JDK 21

PostgreSQL 14+

Maven 3.6+

📂 Base de datos

Ejecuta en PostgreSQL:

CREATE DATABASE catalogo_libros;
CREATE USER literalura_user WITH PASSWORD 'tu_password';
GRANT ALL PRIVILEGES ON DATABASE catalogo_libros TO literalura_user;


Configura el application.properties con tus credenciales.

🚀 Ejecución

Compilar y ejecutar la aplicación:

mvn clean compile
mvn spring-boot:run

📋 Menú principal (funcionalidades)

🔍 Buscar libros por título → En la base de datos local

📖 Listar libros registrados → Todos los almacenados

👥 Listar autores registrados → Autores en BD

🎂 Listar autores vivos en un año → Consulta por año

🌍 Listar libros por idioma → Filtrar por idioma (es, en, fr, etc.)

⬇️ Buscar y guardar libro → Consulta en Gutendex y persistencia

🔎 Buscar en Gutendex → Solo consulta (sin guardar)

📖 Ejemplos de búsqueda:

"Dracula"

"Don Quijote"

"Sherlock Holmes"

"Romeo and Juliet"

🌐 API Gutendex

URL Base: https://gutendex.com/books/

Búsqueda por título: /books/?search={titulo}

Filtrado por idioma: /books/?languages={idioma}

📈 Futuras mejoras

💻 Interfaz web con Spring MVC

🔐 Sistema de autenticación de usuarios

📊 Exportación de datos a PDF/Excel

📚 Estadísticas de lectura

🤖 Sistema de recomendaciones

🙏 Agradecimientos

📚 Gutendex API por el acceso a libros de dominio público

🍃 Spring Boot por el framework

🎓 Alura Latam por el desafío


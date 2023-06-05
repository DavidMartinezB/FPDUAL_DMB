# API POKEMON POR DAVID MARTÍNEZ

Este proyecto es una API para administrar datos de Pokemon. Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en objetos Pokemon.

## Tecnologías utilizadas
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL (asumiendo como base de datos)

## Requisitos previos
- Java Development Kit (JDK) instalado
- MySQL instalado y en funcionamiento
- Base de datos MySQL creada para el proyecto

## Endpoints de la API

### `GET /pokemondb/check`
- Descripción: Verifica si la API está en funcionamiento.
- Respuesta: "hola pokemondb"

### `GET /pokemondb/pokemon`
- Descripción: Obtiene todos los Pokemon.
- Respuesta: Array de objetos Pokemon.

### `GET /pokemondb/pokemon/{numero_pokedex}`
- Descripción: Obtiene un Pokemon por su número de Pokedex.
- Variable de ruta:
  - `numero_pokedex`: Número de Pokedex del Pokemon.
- Respuesta: Objeto Pokemon.

### `POST /pokemondb/pokemon/save`
- Descripción: Guarda un nuevo Pokemon.
- Cuerpo de la solicitud: Objeto Pokemon (formato JSON).
- Respuesta: Objeto Pokemon guardado.

### `DELETE /pokemondb/pokemon/delete/{numero_pokedex}`
- Descripción: Elimina un Pokemon por su número de Pokedex.
- Variable de ruta:
  - `numero_pokedex`: Número de Pokedex del Pokemon.
- Respuesta: Mensaje de éxito o fallo.



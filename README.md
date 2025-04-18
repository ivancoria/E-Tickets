<h1 align="center">E-Tickets 🎟️</h1>

E-Tickets es una aplicación backend diseñada para la gestión de eventos y venta de entradas electrónicas. Implementa un sistema basado en roles donde los __Organizadores__ pueden crear, administrar eventos, mientras que los __Usuarios__ exploran eventos y compran sus entradas de forma segura.

## Índice

* [Stack Tecnológico](#stack-tecnológico)

* [Dependencias](#dependencias)

* [Instalación y Configuración](#instalación-y-configuración)

* [Documentación (Wiki y API)](#documentación-wiki-y-api)

* [Convenciones del Proyecto](#convenciones-del-proyecto)

## Stack Tecnológico
* **Lenguaje:** Java 17
* **Framework:** Spring Boot 3.4.3
* **Seguridad:** Spring Security 6.4.3,  jjwt 0.12.6 (JWT)
* **Base de Datos:** MySQL 8
* **Acceso a Datos:** Spring Data JPA (Hibernate)
* **Build Tool:** Maven 3.9
* **Documentación API:** SpringDoc OpenAPI (Swagger UI)
* **Testing:** _Próximamente..._
* **Docker:** Contenedores configurados para app y base de datos

## Dependencias
* JDK 17
* Spring Boot 3.4.3
* Maven 3.9+
* Gestor de base de datos MySQL 8

##  Instalación y Configuración
* **_Próximamente..._**

##  Documentación (Wiki y API)

* **Wiki del Proyecto:** Para algunos detalles técnicos, documentación de los endpoints, consulta la [**Wiki de este repositorio**](https://github.com/ivancoria/E-Tickets/wiki).

* **Documentación de la API (Swagger):** La especificación completa y la interfaz interactiva para probar los endpoints están disponibles una vez que la aplicación está corriendo en (puerto por defecto: 8080):
 `localhost:8080/swagger-ui.html`

##  Convenciones del Proyecto
Los **Mensajes de Commits** deberán seguir la [**Conventional Commits**](https://www.conventionalcommits.org/en/v1.0.0/).

Los títulos de los **commits** deben ser precedidos por `"<categoria>: "` de la siguiente manera:
```
<categoria>: Título del commit
```
Donde `<categoria>` refiere a uno de los siguientes casos:

* **feat:** Una nueva *feature*.
* **fix:** Un arreglo de un *bug*.
* **docs:** Cambios en la documentación.
* **style:** Cambios que no afectan al código de manera funcional.
* **refactor:** Cambios que no arreglan errores o agregan *features*.
* **test:** Cambios que agregan tests.

Si el cambio no encaja en ninguna de estas categorías, se puede usar un prefijo más genérico.


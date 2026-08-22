# G9-LATAM-TEAM-52
# TechMind – Organización Inteligente del Conocimiento Técnico

## Descripción
TechMind es una solución que organiza inteligentemente contenido técnico —artículos, documentación, apuntes y tutoriales— usando Ciencia de Datos para clasificarlo por tema y extraer palabras clave automáticamente, entregando el resultado en formato JSON a través de una API REST; el equipo lo construye combinando un modelo de TF-IDF + Regresión Logística servido desde un microservicio FastAPI, un back-end en Spring Boot que lo consume, e integración con OCI (Object Storage y Compute) para almacenar el modelo y alojar el servicio.

## Arquitectura
<img width="739" height="576" alt="image" src="https://github.com/user-attachments/assets/39e68973-0f53-411a-870d-d5825b62ca4f"/>

## Cómo ejecutar el proyecto

### Requisitos previos
- JDK 21 (recomendado: distribución 21.0.12)
- IntelliJ IDEA (u otro IDE con soporte para Maven/Spring Boot)

### Pasos

1. **Clonar el repositorio**<br/>
```bash
   git clone https://github.com/No-Country-simulation/G9-LATAM-TEAM-52
```

2. **Configurar el JDK**<br/><br/>
   El proyecto está construido con JDK 21. Al abrir el proyecto en IntelliJ, seleccionar JDK 21 como SDK del proyecto (`File → Project Structure → SDK`).

3. **Verificar la configuración de conexión**<br/><br/>
   Por defecto, el proyecto apunta a la base de datos y al microservicio de clasificación ya desplegados en OCI. No es necesario configurar nada adicional para ejecutarlo tal como está.

   - Para usar una base de datos distinta: modificar los datos de conexión en `application.properties`, ubicado en `src/main/resources/`.
   - Para apuntar a otra instancia del microservicio de clasificación: modificar la URL en `src/main/java/com/HackathonONEG9_52/HackathonG52/domain/pythonapi/`.

4. **Ejecutar el proyecto**<br/><br/>
   Correr la clase principal ubicada en:
   `src/main/java/com/HackathonONEG9_52/HackathonG52/HackathonG52Application.java`

   Al iniciar, el navegador se abre automáticamente en la pantalla principal de clasificación.

### Cómo funciona la clasificación

El back-end envía el título y texto ingresados al microservicio FastAPI (alojado en OCI Compute), que devuelve la categoría, probabilidad y palabras clave usando el último modelo cargado.

### Uso de la interfaz

- **Pantalla principal**: incluye un filtro de búsqueda desplegable y una tabla con las consultas ya almacenadas en la base de datos.
- **Nueva clasificación**: el botón al final de la página abre un modal donde se puede escribir el texto directamente o cargar un archivo. Al confirmar, la consulta se agrega a la tabla.
- **Detalle de una consulta**: al seleccionar una fila de la tabla, se abre un modal con la información completa de esa clasificación.

### Actualizar el modelo (opcional, no necesario para ejecutar el proyecto)

El modelo y el vectorizador vigentes se generan desde el notebook consolidado de Data Science en Google Colab. Al ejecutar las celdas del notebook, se generan ambos archivos (`.pkl`), que luego se suben al microservicio desplegado para reemplazar la versión anterior.

## Cómo usar la API
### POST /contenido
**Entrada:**
```
json
{ "titulo": "...", "texto": "..." }
```
**Salida:**
```
json
{ "categoria": "...", "probabilidad": 0.89, "informacion_adicional": [...] }
```

## Ejemplos de uso

### Ejemplo 1 — Categoría Back-End
**Request:**
```
POST /contenido
Content-Type: application/json

{
  "titulo": "Creación de una API REST con Spring Boot",
  "texto": "En este tutorial se explica cómo construir una API REST utilizando Java y el framework Spring Boot, cubriendo la configuración de controladores, la inyección de dependencias y la conexión con una base de datos mediante Spring Data JPA."
}
```

**Response:**
```
{
  "categoria": "Backend",
  "probabilidad": 0.91,
  "informacion_adicional": ["Spring Boot", "Java", "API REST", "Spring Data JPA", "controladores"]
}
```

### Ejemplo 2 — Categoría Back-End
**Request:**
```
POST /contenido
Content-Type: application/json

{
  "titulo": "Arquitectura de componentes en Angular",
  "texto": "Uso de TypeScript, RxJS y Signals para construir interfaces web reactivas"
}
```

**Response:**
```
{
  "categoria": "Frontend",
  "probabilidad": 0.7,
  "informacion_adicional": [
    "en",
    "interfaces",
    "signals",
    "para",
    "web"
  ]
}
```

### Ejemplo 3 — Categoría Data Science
**Request:**
```
POST /contenido
Content-Type: application/json

{
  "titulo": "Concurrencia y Goroutines en Go",
  "texto": "Creación de microservicios de alto rendimiento y manejo de canales en Golang"
}
```

**Response:**
```
{
  "categoria": "Data Science",
  "probabilidad": 0.3,
  "informacion_adicional": [
    "en"
  ]
}
```

## Modelo de Data Science
- Dataset utilizado:<br/>
  [StackSample, de Stack Overflow](https://www.kaggle.com/datasets/stackoverflow/stacksample/data)
  Se utilizan desde 2000 a 50.000
  
- Enfoque (TF-IDF + Regresión Logística):<br/>
  El algoritmo con mejor desempeño para cubrir las necesidades del proyecto es Regresión Logística

- Métricas de evaluación:<br/>
  El porcentaje de exactitud con el que cuenta el modelo, contando con 6 categorías y al menos 7 sub categorías por cada una, es de 94.83%. La especificación por categoría se encuentra en la siguiente imagen (Además, se puede consultar la documentación anexa al proyecto).<br/><br/>

  <img width="559" height="385" alt="image" src="https://github.com/user-attachments/assets/d0445949-e14d-421e-baa3-2adbd17878b2" />



## Integración con OCI

### - Object Storage

Almacena el modelo de clasificación y el vectorizador entrenados (.pkl), junto con el dataset utilizado para el entrenamiento vigente. Cada nueva versión se sube reemplazando la anterior, gestionado a través de OCI Functions (ver abajo).

- Contenido almacenado: modelo serializado (joblib), vectorizador TF-IDF, dataset de entrenamiento
- Consumido por: el microservicio FastAPI, que carga estos archivos al iniciar

### - OCI Compute

Aloja el microservicio de clasificación (FastAPI + Uvicorn), consumido por el back-end en Spring Boot.

- Servicio: corre como servicio systemd (api.service), lo que permite disponibilidad continua y reinicio automático ante caídas
- Acceso: IP pública fija, con firewall configurado a nivel de Security List (OCI) y del sistema operativo
- Monitoreo: seguimiento continuo desde el primer despliegue (27 de julio), documentado con reportes de uptime
- Pruebas de rendimiento: el sistema soporta cargas de 50, 70 y 100 peticiones concurrentes con tiempos de respuesta promedio de 589 ms, 792 ms y 1.122 ms respectivamente, sin peticiones fallidas

### - OCI Functions

Implementado y operativo. Permite al equipo de Data Science subir nuevas versiones del modelo y vectorizador a Object Storage de forma autónoma, sin depender del acceso directo de un integrante específico a la cuenta de OCI.

## Equipo

- Matias Manriquez -- Project Manager
- Dennisse Pailamilla -- Data Scientist
- Jonathan Mangano -- Data Scientist
- Catalina Raposo -- Data
- Marcos Mazzanti -- Backend Developer
- Tomas Yamil -- Backend Developer
- Jeremy Duran -- Full Stack Developer - OCI manager

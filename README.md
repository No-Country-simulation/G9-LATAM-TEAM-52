# G9-LATAM-TEAM-52
# TechMind – Organización Inteligente del Conocimiento Técnico

## Descripción
TechMind es una solución que organiza inteligentemente contenido técnico —artículos, documentación, apuntes y tutoriales— usando Ciencia de Datos para clasificarlo por tema y extraer palabras clave automáticamente, entregando el resultado en formato JSON a través de una API REST; el equipo lo construye combinando un modelo de TF-IDF + Regresión Logística servido desde un microservicio FastAPI, un back-end en Spring Boot que lo consume, e integración con OCI (Object Storage y Compute) para almacenar el modelo y alojar el servicio.

## Arquitectura
<img width="664" height="282" alt="image" src="https://github.com/user-attachments/assets/119ee8a7-4d82-4012-b3fc-f00e5e54e459" />


## Cómo ejecutar el proyecto
### Requisitos
- Java 21, Spring Boot 4.1
- Python 3.x, dependencias en requirements.txt

### Pasos
1. Clonar el repositorio
2. [instrucciones back-end]
3. [instrucciones microservicio]

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
- Dataset utilizado
- Enfoque (TF-IDF + Regresión Logística)
- Métricas de evaluación

## Integración con OCI

### - Object Storage

### - OCI Copmute

### - OCI Functions

## Equipo

- Matias Manriquez -- Project Manager
- Dennisse Pailamilla -- Data Scientist
- Jonathan Mangano -- Data Scientist
- Catalina Raposo -- Data
- Marcos Mazzanti -- Backend Developer
- Tomas Yamil -- Backend Developer
- Jeremy Duran -- Full Stack Developer - OCI manager

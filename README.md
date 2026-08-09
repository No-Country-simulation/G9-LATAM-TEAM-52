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
```json
{ "titulo": "...", "texto": "..." }
```
**Salida:**
```json
{ "categoria": "...", "probabilidad": 0.89, "informacion_adicional": [...] }
```

## Ejemplos de uso (mínimo 3, obligatorio)
[request/response reales, no inventados]

## Modelo de Data Science
- Dataset utilizado
- Enfoque (TF-IDF + Regresión Logística)
- Métricas de evaluación

## Integración con OCI
- Servicio usado (Object Storage) y para qué

## Equipo

- Matias Manriquez -- Project Manager
- Dennisse Pailamilla -- Data Scientist
- Jonathan Mangano -- Data Scientist
- Catalina Raposo -- Data
- Marcos Mazzanti -- Backend Developer
- Tomas Yamil -- Backend Developer
- Jeremy Duran -- Full Stack Developer - OCI manager

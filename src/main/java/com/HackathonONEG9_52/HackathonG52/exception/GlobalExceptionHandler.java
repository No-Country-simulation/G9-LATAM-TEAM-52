package com.HackathonONEG9_52.HackathonG52.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Ataja cuando el usuario envía un título > 250 caracteres o campos vacíos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarValidaciones(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores); // Devuelve error 400
    }

    // 2. Ataja si la API de Python en OCI está caída o da error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> manejarErrorGenerico(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Ocurrió un problema al procesar la solicitud con el servicio de IA.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error); // Devuelve error 500
    }

    // 3. Ataja fallos de clasificación específicos o de respuesta errónea de la API de Python
    @ExceptionHandler(ClasificacionException.class)
    public ResponseEntity<Map<String, String>> manejarClasificacionException(ClasificacionException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error); // Devuelve error 500
    }
}
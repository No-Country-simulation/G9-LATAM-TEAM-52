package com.HackathonONEG9_52.HackathonG52.exception;

/**
 * Excepción personalizada para representar fallos durante el proceso de clasificación
 * o comunicación con la API externa de Python.
 * 
 * Al heredar de RuntimeException (excepción no verificada), no obliga a declarar
 * "throws" en las firmas de los métodos, facilitando un código más limpio.
 */
public class ClasificacionException extends RuntimeException {

    /**
     * Constructor para lanzar la excepción únicamente con un mensaje descriptivo.
     * Ejemplo de uso: throw new ClasificacionException("La API devolvió una respuesta inválida");
     * 
     * @param message Mensaje que describe el error ocurrido.
     */
    public ClasificacionException(String message) {
        super(message);
    }

    /**
     * Constructor para lanzar la excepción con un mensaje y además adjuntar la causa original.
     * Esto es útil para encapsular excepciones de bajo nivel (como IOException o InterruptedException)
     * sin perder la traza original del error.
     * 
     * @param message Mensaje explicativo personalizado.
     * @param cause La excepción original (causa raíz del fallo).
     */
    public ClasificacionException(String message, Throwable cause) {
        super(message, cause);
    }
}
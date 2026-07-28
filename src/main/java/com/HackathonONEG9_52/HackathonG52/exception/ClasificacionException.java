package com.HackathonONEG9_52.HackathonG52.exception;

//Excepción personalizada para representar fallos durante el proceso de clasificación
//o comunicación con la API externa de Python.
public class ClasificacionException extends RuntimeException {
    
    //Constructor para lanzar la excepción únicamente con un mensaje descriptivo.
    public ClasificacionException(String message) {
        super(message);
    }

    //Constructor para lanzar la excepción con un mensaje y además adjuntar la causa original.
    public ClasificacionException(String message, Throwable cause) {
        super(message, cause);
    }
}
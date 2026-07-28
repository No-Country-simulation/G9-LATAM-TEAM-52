package com.HackathonONEG9_52.HackathonG52.domain.contenido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ContenidoDTO(
        @NotBlank(message = "El título no puede estar vacío")
        @Size(max = 250, message = "El título no puede superar los 250 caracteres")
        @Pattern(
                regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑüÜ .,;:\\-_()¿?¡!]+$",
                message = "El título contiene caracteres especiales no permitidos"
        )
        String titulo,

        @NotBlank(message = "El texto no puede estar vacío")
        String texto
) {
}
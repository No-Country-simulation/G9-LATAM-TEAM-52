package com.HackathonONEG9_52.HackathonG52.domain.contenido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ContenidoDTO(
        @NotBlank(message = "El título no puede estar vacío")
        @Size(max = 250, message = "El título no puede superar los 250 caracteres")
        String titulo,

        @NotBlank(message = "El texto no puede estar vacío")
        String texto
) {
}
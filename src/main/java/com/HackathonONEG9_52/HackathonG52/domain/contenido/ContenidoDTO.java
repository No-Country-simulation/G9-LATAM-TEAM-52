package com.HackathonONEG9_52.HackathonG52.domain.contenido;

import jakarta.validation.constraints.NotBlank;

public record ContenidoDTO(
        @NotBlank String titulo,
        @NotBlank String texto
) {
}

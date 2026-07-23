package com.HackathonONEG9_52.HackathonG52.domain.clasificacion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ClasificacionDTO(
        @NotBlank String categoria,
        @NotNull double probabilidad,
        @NotNull List<String> informacionAdicional
) {
}

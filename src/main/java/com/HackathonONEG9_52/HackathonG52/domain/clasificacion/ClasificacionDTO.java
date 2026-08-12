package com.HackathonONEG9_52.HackathonG52.domain.clasificacion;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ClasificacionDTO(
        String titulo,
        String texto,
        @NotBlank String categoria,
        @NotNull Double probabilidad,
        @JsonProperty("informacion_adicional") @NotNull List<String> informacionAdicional
) {
    public ClasificacionDTO(String categoria, Double probabilidad, List<String> informacionAdicional) {
        this(null, null, categoria, probabilidad, informacionAdicional);
    }
}
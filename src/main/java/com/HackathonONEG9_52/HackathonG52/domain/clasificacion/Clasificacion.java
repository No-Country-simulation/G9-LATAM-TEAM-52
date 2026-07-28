package com.HackathonONEG9_52.HackathonG52.domain.clasificacion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Clasificacion {

    private String categoria;
    private Double probabilidad;

    @JsonProperty("informacion_adicional") // <--- ESTO MAPEA EL CAMPO DE PYTHON
    private List<String> informacionAdicional;

    // Devolvemos un clasificacionDTO por medio de un constructor
    public ClasificacionDTO aDTO() {
        return new ClasificacionDTO(this.categoria, this.probabilidad, this.informacionAdicional);
    }
}
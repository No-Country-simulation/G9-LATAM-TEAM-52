package com.HackathonONEG9_52.HackathonG52.domain.clasificacion;

import java.util.List;

public class Clasificacion {
    private String categoria;
    private double probabilidad;
    private List<String> informacionAdicional;

    //Devolvemos un clasificacionDTO por medio de un constructor
    public ClasificacionDTO aDTO() {
        return new ClasificacionDTO(this.categoria, this.probabilidad, this.informacionAdicional);
    }
}

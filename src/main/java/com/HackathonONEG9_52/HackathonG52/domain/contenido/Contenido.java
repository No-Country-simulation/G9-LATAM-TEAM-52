package com.HackathonONEG9_52.HackathonG52.domain.contenido;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Contenido {

    private String titulo;
    private String texto;

    //Constructor de contenidoDTO a contenido
    public Contenido(ContenidoDTO dto) {
        this.titulo = dto.titulo();
        this.texto = dto.texto();
    }
}

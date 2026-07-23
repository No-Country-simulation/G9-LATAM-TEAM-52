package com.HackathonONEG9_52.HackathonG52.controller;

import com.HackathonONEG9_52.HackathonG52.domain.clasificacion.Clasificacion;
import com.HackathonONEG9_52.HackathonG52.domain.clasificacion.ClasificacionDTO;
import com.HackathonONEG9_52.HackathonG52.domain.contenido.Contenido;
import com.HackathonONEG9_52.HackathonG52.domain.contenido.ContenidoDTO;
import com.HackathonONEG9_52.HackathonG52.domain.pythonapi.PythonAPI;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contenido")
@RequiredArgsConstructor //Genera el constructor automaticamente para los atributos final
public class ContenidoController {

    private final PythonAPI pythonAPI;

    @PostMapping
    public ResponseEntity<ClasificacionDTO> clasificarContenido(@RequestBody @Valid ContenidoDTO dto) {

        //Conversion dto a objeto(Contenido)
        Contenido contenido = new Contenido(dto);

        //Llamada a la API para clasificar
        Clasificacion clasificacion = pythonAPI.clasificar(contenido);

        //Convertimos el objeto(Clasificacion) a dto para la salida
        return ResponseEntity.ok(clasificacion.aDTO());
    }
}

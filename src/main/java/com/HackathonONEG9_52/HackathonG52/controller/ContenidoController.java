package com.HackathonONEG9_52.HackathonG52.controller;

import com.HackathonONEG9_52.HackathonG52.domain.clasificacion.ClasificacionDTO;
import com.HackathonONEG9_52.HackathonG52.domain.contenido.ContenidoDTO;
import com.HackathonONEG9_52.HackathonG52.domain.contenido.ContenidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contenido")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ContenidoController {

    private final ContenidoService contenidoService;

    @PostMapping
    public ResponseEntity<ClasificacionDTO> clasificarContenido(@RequestBody @Valid ContenidoDTO dto) {
        ClasificacionDTO clasificacionDTO = contenidoService.guardarYClasificar(dto);
        return ResponseEntity.ok(clasificacionDTO);
    }

    @GetMapping
    public ResponseEntity<List<ClasificacionDTO>> obtenerContenidos(@RequestParam(required = false) String query) {
        List<ClasificacionDTO> contenidos = contenidoService.buscar(query);
        return ResponseEntity.ok(contenidos);
    }
}


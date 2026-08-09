package com.HackathonONEG9_52.HackathonG52.controller;

import com.HackathonONEG9_52.HackathonG52.domain.categoria.Categoria;
import com.HackathonONEG9_52.HackathonG52.domain.categoria.CategoriaRepository;
import com.HackathonONEG9_52.HackathonG52.domain.clasificacion.Clasificacion;
import com.HackathonONEG9_52.HackathonG52.domain.clasificacion.ClasificacionDTO;
import com.HackathonONEG9_52.HackathonG52.domain.clasificacion.ClasificacionRepository;
import com.HackathonONEG9_52.HackathonG52.domain.contenido.Contenido;
import com.HackathonONEG9_52.HackathonG52.domain.contenido.ContenidoDTO;
import com.HackathonONEG9_52.HackathonG52.domain.contenido.ContenidoRepository;
import com.HackathonONEG9_52.HackathonG52.domain.pythonapi.PythonAPI;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/contenido")
@RequiredArgsConstructor //Genera el constructor automaticamente para los atributos final
public class ContenidoController {

    private final PythonAPI pythonAPI;
    private final ContenidoRepository contenidoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ClasificacionRepository clasificacionRepository;

    @PostMapping
    public ResponseEntity<ClasificacionDTO> clasificarContenido(@RequestBody @Valid ContenidoDTO dto) {

        //Conversion dto a objeto(Contenido)
        Contenido contenido = new Contenido(dto);
        Contenido contenidoGuardado = contenidoRepository.save(contenido);

        //Llamada a la API para clasificar
        Clasificacion clasificacion = pythonAPI.clasificar(contenidoGuardado);

        //Obtenemos una categoria ya existe o la creamos
        String nombreCategoria = clasificacion.getCategoria().getDescripcion();
        Categoria categoriaEntity = obtenerOGuardarCategoria(nombreCategoria);

        //Asignamos las relaciones de contenido y categoria
        clasificacion.setContenido(contenidoGuardado);
        clasificacion.setCategoria(categoriaEntity);

        //Guardamos la clasificacion
        Clasificacion clasificacionGuardada = clasificacionRepository.save(clasificacion);

        //Devolvemos DTO al usuario
        return ResponseEntity.ok(clasificacionGuardada.aDTO());
    }

    //Metodo especializado en buscar si la cateogira ya existe o si se debe crear
    private Categoria obtenerOGuardarCategoria(String nombreCategoria) {
        Optional<Categoria> categoriaExistente = categoriaRepository.findByDescripcion(nombreCategoria);

        if (categoriaExistente.isPresent()) {
            return categoriaExistente.get();
        } else {
            Categoria nuevaCategoria = new Categoria(nombreCategoria);
            return categoriaRepository.save(nuevaCategoria);
        }

    }

}

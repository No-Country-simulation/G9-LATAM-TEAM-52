package com.HackathonONEG9_52.HackathonG52.domain.contenido;

import com.HackathonONEG9_52.HackathonG52.domain.categoria.Categoria;
import com.HackathonONEG9_52.HackathonG52.domain.categoria.CategoriaRepository;
import com.HackathonONEG9_52.HackathonG52.domain.clasificacion.Clasificacion;
import com.HackathonONEG9_52.HackathonG52.domain.clasificacion.ClasificacionDTO;
import com.HackathonONEG9_52.HackathonG52.domain.clasificacion.ClasificacionRepository;
import com.HackathonONEG9_52.HackathonG52.domain.pythonapi.PythonAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContenidoService {

    private final PythonAPI pythonAPI;
    private final ContenidoRepository contenidoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ClasificacionRepository clasificacionRepository;

    // 1. Guarda el contenido, consulta a OCI y vincula las 3 tablas en Supabase
    public ClasificacionDTO guardarYClasificar(ContenidoDTO dto) {
        // Buscamos si ya existe el mismo texto para no duplicar ni gastar llamadas de IA
        Optional<Contenido> contenidoExistente = contenidoRepository.findByTituloAndTexto(dto.titulo(), dto.texto());
        if (contenidoExistente.isEmpty()) {
            contenidoExistente = contenidoRepository.findByTexto(dto.texto());
        }

        // Si ya está guardado, devolvemos la clasificación vieja directamente
        if (contenidoExistente.isPresent()) {
            Optional<Clasificacion> clasificacionExistente = clasificacionRepository.findByContenido(contenidoExistente.get());
            if (clasificacionExistente.isPresent()) {
                return clasificacionExistente.get().aDTO();
            }
        }

        Contenido contenido = new Contenido(dto);
        Contenido contenidoGuardado = contenidoRepository.save(contenido);

        Clasificacion clasificacion = pythonAPI.clasificar(contenidoGuardado);

        String nombreCategoria = clasificacion.getCategoria().getDescripcion();
        Categoria categoriaEntity = obtenerOGuardarCategoria(nombreCategoria);

        clasificacion.setContenido(contenidoGuardado);
        clasificacion.setCategoria(categoriaEntity);

        Clasificacion clasificacionGuardada = clasificacionRepository.save(clasificacion);

        return clasificacionGuardada.aDTO();
    }

    // 2. Trae la lista completa para la tabla o filtra si se escribe en el buscador
    public List<ClasificacionDTO> buscar(String query) {
        List<Clasificacion> todas = clasificacionRepository.findAll();

        if (query == null || query.isBlank()) {
            return todas.stream().map(Clasificacion::aDTO).toList();
        }

        // Filtro dinámico por coincidencia en texto o categoría
        return todas.stream()
                .filter(c -> (c.getContenido() != null && c.getContenido().getTexto().toLowerCase().contains(query.toLowerCase())) ||
                        (c.getCategoria() != null && c.getCategoria().getDescripcion().toLowerCase().contains(query.toLowerCase())))
                .map(Clasificacion::aDTO)
                .toList();
    }

    private Categoria obtenerOGuardarCategoria(String nombreCategoria) {
        Optional<Categoria> categoriaExistente = categoriaRepository.findByDescripcion(nombreCategoria);
        return categoriaExistente.orElseGet(() -> categoriaRepository.save(new Categoria(nombreCategoria)));
    }
}
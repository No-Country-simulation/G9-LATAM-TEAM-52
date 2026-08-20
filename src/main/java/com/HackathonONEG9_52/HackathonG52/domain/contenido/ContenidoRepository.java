package com.HackathonONEG9_52.HackathonG52.domain.contenido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContenidoRepository extends JpaRepository<Contenido, Long> {

    List<Contenido> findByTextoContainingIgnoreCaseOrCategoriaContainingIgnoreCase(String texto, String categoria);

    // Buscadores para evitar guardar textos repetidos
    Optional<Contenido> findByTituloAndTexto(String titulo, String texto);

    Optional<Contenido> findByTexto(String texto);
}
package com.HackathonONEG9_52.HackathonG52.domain.clasificacion;

import com.HackathonONEG9_52.HackathonG52.domain.contenido.Contenido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClasificacionRepository extends JpaRepository<Clasificacion, Long> {

    Optional<Clasificacion> findByContenido(Contenido contenido);
}


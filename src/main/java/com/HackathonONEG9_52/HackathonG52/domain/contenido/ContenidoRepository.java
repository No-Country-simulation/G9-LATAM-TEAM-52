package com.HackathonONEG9_52.HackathonG52.domain.contenido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContenidoRepository extends JpaRepository<Contenido, Long> {
}

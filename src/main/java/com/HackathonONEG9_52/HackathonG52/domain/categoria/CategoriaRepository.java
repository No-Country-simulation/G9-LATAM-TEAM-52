package com.HackathonONEG9_52.HackathonG52.domain.categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    //Busca Categoria por descripcion
    Optional<Categoria> findByDescripcion(String descripcion);
}

package com.HackathonONEG9_52.HackathonG52.domain.contenido;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "contenido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contenido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contenido_id")
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String texto;

    // --- CAMPOS DE CLASIFICACIÓN DE IA ---
    private String categoria;

    private Double probabilidad;

    @ElementCollection
    @CollectionTable(name = "contenido_palabras_clave", joinColumns = @JoinColumn(name = "contenido_id"))
    @Column(name = "palabra_clave")
    private List<String> informacionAdicional;

    // Constructor desde DTO
    public Contenido(ContenidoDTO dto) {
        this.titulo = dto.titulo();
        this.texto = dto.texto();
    }
}
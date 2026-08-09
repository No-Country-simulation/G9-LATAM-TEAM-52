package com.HackathonONEG9_52.HackathonG52.domain.contenido;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    //Utilizamos la anotacion TEXT debido a que permite guardar textos de gran tamaño
    @Column(columnDefinition = "TEXT", nullable = false)
    private String texto;

    //Constructor de contenidoDTO a contenido
    public Contenido(ContenidoDTO dto) {
        this.titulo = dto.titulo();
        this.texto = dto.texto();
    }
}

package com.HackathonONEG9_52.HackathonG52.domain.categoria;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private Long id;

    //Anotacion para que la descripcion no pueda ser nula y ademas sea unica.
    @Column(nullable = false, unique = true)
    private String descripcion;

    public Categoria(String descripcion) {
        this.descripcion = descripcion;
    }
}

package com.HackathonONEG9_52.HackathonG52.domain.clasificacion;

import com.HackathonONEG9_52.HackathonG52.domain.categoria.Categoria;
import com.HackathonONEG9_52.HackathonG52.domain.contenido.Contenido;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "clasificacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Clasificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clasificacion_id")
    private Long id;

    private Double probabilidad;

    //Anotacion para indicar que muchas clasificacion pueden apuntar a una categoria, y una categoria puede tener muchas clasificaciones
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    //Anotacion para indicar que un contenido puede apuntar a una sola clasificaicon y viceversa
    @OneToOne
    @JoinColumn(name = "contenido_id", nullable = false, unique = true)
    private Contenido contenido;

    //Anotacion para indicar este valor viene de a muchos, encargandose de las relaciones de las palabras de informacion adicional
    @JsonProperty("informacion_adicional")
    @ElementCollection
    @CollectionTable(name = "palabras_clave", joinColumns = @JoinColumn(name = "clasificacion_id"))
    @Column(name = "palabra")
    private List<String> informacionAdicional;

    // Devolvemos un clasificacionDTO por medio de un constructor
    public ClasificacionDTO aDTO() {
        //Transformamos una categoria en un string para pasarlo junto al dto de clasificacion
        String nombreCategoria = (this.categoria != null) ? this.categoria.getDescripcion() : null;
        return new ClasificacionDTO(nombreCategoria, this.probabilidad, this.informacionAdicional);
    }
}
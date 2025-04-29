package com.example.pet_events.model;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "PARTICIPANTE")
public class Participante extends RepresentationModel<Participante> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotBlank(message = "El nombre del participante no puede estar vacío")
    @Size(max = 255, message = "El nombre del participante no puede tener más de 255 caracteres")
    @Column(name = "NOMBRE_PARTICIPANTE", nullable = false, length = 255)
    private String nombre;

    @Size(max = 255, message = "El nombre de la mascota no puede tener más de 255 caracteres")
    @Column(name = "NOMBRE_MASCOTA", length = 255)
    private String nombreMascota;

    @Size(max = 255, message = "El tipo de mascota no puede tener más de 255 caracteres")
    @Column(name = "TIPO_MASCOTA", length = 255)
    private String tipoMascota;

    @Min(value = 0, message = "La edad de la mascota no puede ser negativa")
    @Column(name = "EDAD_MASCOTA")
    private int edadMascota;

    @ManyToOne
    @JoinColumn(name = "ID_EVENTO", nullable = false)
    @NotNull(message = "El evento no puede ser nulo")
    private Evento evento;

    // Constructor vacío (requerido por JPA)
    public Participante() {}

    public Participante(Long id, String nombre, String nombreMascota, String tipoMascota, int edadMascota, Evento evento) {
        this.id = id;
        this.nombre = nombre;
        this.nombreMascota = nombreMascota;
        this.tipoMascota = tipoMascota;
        this.edadMascota = edadMascota;
        this.evento = evento;
    }

    // Getters and Setters
    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("nombre")
    public String getName() {
        return nombre;
    }

    @JsonProperty("nombre")
    public void setName(String nombre) {
        this.nombre = nombre;
    }

    @JsonProperty("nombreMascota")
    public String getPetName() {
        return nombreMascota;
    }

    @JsonProperty("nombreMascota")
    public void setPetName(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    @JsonProperty("tipoMascota")
    public String getPetType() {
        return tipoMascota;
    }

    @JsonProperty("tipoMascota")
    public void setPetType(String tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    @JsonProperty("edadMascota")
    public int getPetAge() {
        return edadMascota;
    }

    @JsonProperty("edadMascota")
    public void setPetAge(int edadMascota) {
        this.edadMascota = edadMascota;
    }

    @JsonProperty("evento")
    public Evento getEvento() {
        return evento;
    }

    @JsonProperty("evento")
    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}

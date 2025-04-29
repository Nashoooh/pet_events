package com.example.pet_events.model;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "EVENTO")
public class Evento extends RepresentationModel<Evento> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotBlank(message = "El nombre del evento no puede estar vacío")
    @Size(max = 255, message = "El nombre del evento no puede tener más de 255 caracteres")
    @Column(name = "NOMBRE", nullable = false, length = 255)
    private String nombreEvento;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 1000, message = "La descripción no puede tener más de 1000 caracteres")
    @Column(name = "DESCRIPCION", nullable = false, length = 1000)
    private String descripcion;

    @FutureOrPresent(message = "La fecha del evento no puede ser anterior a la fecha actual")
    @Column(name = "FECHA", nullable = false)
    private LocalDate fecha;

    @NotBlank(message = "La ubicación no puede estar vacía")
    @Size(max = 255, message = "La ubicación no puede tener más de 255 caracteres")
    @Column(name = "UBICACION", nullable = false, length = 255)
    private String ubicacion;

    // Constructor vacío (requerido por JPA)
    public Evento() {}

    public Evento(Long id, String nombreEvento, String descripcion, LocalDate fecha, String ubicacion) {
        this.id = id;
        this.nombreEvento = nombreEvento;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
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

    @JsonProperty("nombreEvento")
    public String getName() {
        return nombreEvento;
    }

    @JsonProperty("nombreEvento")
    public void setName(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    @JsonProperty("descripcion")
    public String getDescription() {
        return descripcion;
    }

    @JsonProperty("descripcion")
    public void setDescription(String descripcion) {
        this.descripcion = descripcion;
    }

    @JsonProperty("fecha")
    public LocalDate getDate() {
        return fecha;
    }

    @JsonProperty("fecha")
    public void setDate(LocalDate fecha) {
        this.fecha = fecha;
    }

    @JsonProperty("ubicacion")
    public String getLocation() {
        return ubicacion;
    }

    @JsonProperty("ubicacion")
    public void setLocation(String ubicacion) {
        this.ubicacion = ubicacion;
    }

}

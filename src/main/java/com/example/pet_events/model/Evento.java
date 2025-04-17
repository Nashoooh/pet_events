package com.example.pet_events.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EVENTO")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 255)
    private String nombreEvento;

    @Column(name = "DESCRIPCION", nullable = false, length = 1000)
    private String descripcion;

    @Column(name = "FECHA", nullable = false)
    private LocalDate fecha;

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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return nombreEvento;
    }

    public void setName(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getDescription() {
        return descripcion;
    }

    public void setDescription(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getDate() {
        return fecha;
    }

    public void setDate(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getLocation() {
        return ubicacion;
    }

    public void setLocation(String ubicacion) {
        this.ubicacion = ubicacion;
    }

}

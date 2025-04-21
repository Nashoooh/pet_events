package com.example.pet_events.model;

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
public class Participante {

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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return nombre;
    }

    public void setName(String nombre) {
        this.nombre = nombre;
    }

    public String getPetName() {
        return nombreMascota;
    }

    public void setPetName(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getPetType() {
        return tipoMascota;
    }

    public void setPetType(String tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public int getPetAge() {
        return edadMascota;
    }

    public void setPetAge(int edadMascota) {
        this.edadMascota = edadMascota;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}

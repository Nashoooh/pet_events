package com.example.pet_events.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParticipanteDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("nombreMascota")
    private String nombreMascota;

    @JsonProperty("tipoMascota")
    private String tipoMascota;

    @JsonProperty("edadMascota")
    private int edadMascota;

    public ParticipanteDTO() {
    }

    public ParticipanteDTO(Long id, String nombre, String nombreMascota, String tipoMascota, int edadMascota) {
        this.id = id;
        this.nombre = nombre;
        this.nombreMascota = nombreMascota;
        this.tipoMascota = tipoMascota;
        this.edadMascota = edadMascota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(String tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public int getEdadMascota() {
        return edadMascota;
    }

    public void setEdadMascota(int edadMascota) {
        this.edadMascota = edadMascota;
    }

}
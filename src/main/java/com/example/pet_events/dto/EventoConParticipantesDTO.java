package com.example.pet_events.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventoConParticipantesDTO {
    
    @JsonProperty("id")
    private Long id;

    @JsonProperty("nombreEvento")
    private String nombre;

    @JsonProperty("fecha")
    private LocalDate fecha;

    @JsonProperty("ubicacion")
    private String lugar;

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("participantes")
    private List<ParticipanteDTO> participantes;

    public EventoConParticipantesDTO() {
    }

    public EventoConParticipantesDTO(Long id, String nombre, LocalDate fecha, String lugar, String descripcion, List<ParticipanteDTO> participantes) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.participantes = participantes;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<ParticipanteDTO> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<ParticipanteDTO> participantes) {
        this.participantes = participantes;
    }


}

package com.example.pet_events.service;

import com.example.pet_events.model.Evento;
import java.util.List;

public interface EventoService {
    List<Evento> getAllEventos();
    Evento getEventoById(Long id);
    Evento createEvento(Evento evento);
    Evento updateEvento(Long id, Evento evento);
    void deleteEvento(Long id);

}
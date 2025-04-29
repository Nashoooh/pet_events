package com.example.pet_events.service;

import com.example.pet_events.model.Evento;
import com.example.pet_events.repository.EventoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EventoServiceImplTest {

    @Mock
    private EventoRepository eventoRepository;

    @InjectMocks
    private EventoServiceImpl eventoService;

    private Evento evento1;
    private Evento evento2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        evento1 = new Evento(1L, "Adopción de Mascotas", "Evento para promover la adopción responsable de mascotas", LocalDate.of(2023, 12, 15), "Parque Central");
        evento2 = new Evento(2L, "Educación sobre Tenencia Responsable", "Charla educativa sobre el cuidado y responsabilidad de tener mascotas", LocalDate.of(2024, 1, 20), "Centro de Convenciones");
    }

    @Test
    void testGetAllEventos() {
        List<Evento> mockEventos = Arrays.asList(evento1, evento2);

        when(eventoRepository.findAll()).thenReturn(mockEventos);

        List<Evento> result = eventoService.getAllEventos();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Adopción de Mascotas", result.get(0).getName());
        verify(eventoRepository, times(1)).findAll();
    }

    @Test
    void testGetEventoById() {
        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento1));

        Evento result = eventoService.getEventoById(1L);

        assertNotNull(result);
        assertEquals("Adopción de Mascotas", result.getName());
        verify(eventoRepository, times(1)).findById(1L);
    }

    @Test
    void testGetEventoByIdNotFound() {
        when(eventoRepository.findById(99L)).thenReturn(Optional.empty());

        Evento result = eventoService.getEventoById(99L);

        assertNull(result);
        verify(eventoRepository, times(1)).findById(99L);
    }

    @Test
    void testCreateEvento() {
        Evento newEvento = new Evento(null, "Taller de Cuidado Animal", "Taller educativo sobre el cuidado responsable de mascotas", LocalDate.of(2024, 2, 10), "Casa de la Cultura");

        when(eventoRepository.save(newEvento)).thenReturn(newEvento);

        Evento result = eventoService.createEvento(newEvento);

        assertNotNull(result);
        assertEquals("Taller de Cuidado Animal", result.getName());
        verify(eventoRepository, times(1)).save(newEvento);
    }

    @Test
    void testUpdateEvento() {
        Evento updatedEvento = new Evento(1L, "Feria de Adopción", "Feria para promover la adopción de mascotas", LocalDate.of(2023, 12, 20), "Parque Municipal");

        when(eventoRepository.existsById(1L)).thenReturn(true);
        when(eventoRepository.save(any(Evento.class))).thenReturn(updatedEvento);

        Evento result = eventoService.updateEvento(1L, updatedEvento);

        assertNotNull(result);
        assertEquals("Feria de Adopción", result.getName());
        verify(eventoRepository, times(1)).existsById(1L);
        verify(eventoRepository, times(1)).save(any(Evento.class));
    }

    @Test
    void testUpdateEventoNotFound() {
        Evento updatedEvento = new Evento(99L, "Evento No Existente", "Descripción", LocalDate.of(2025, 1, 1), "Ubicación");

        when(eventoRepository.existsById(99L)).thenReturn(false);

        Evento result = eventoService.updateEvento(99L, updatedEvento);

        assertNull(result);
        verify(eventoRepository, times(1)).existsById(99L);
        verify(eventoRepository, never()).save(any(Evento.class));
    }

    @Test
    void testDeleteEvento() {
        when(eventoRepository.existsById(1L)).thenReturn(true);

        eventoService.deleteEvento(1L);

        verify(eventoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteEventoNotFound() {
        when(eventoRepository.existsById(99L)).thenReturn(false);

        eventoService.deleteEvento(99L);

        verify(eventoRepository, never()).deleteById(99L);
    }
}
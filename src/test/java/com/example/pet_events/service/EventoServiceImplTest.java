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


}
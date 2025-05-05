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
import static org.mockito.Mockito.*;

public class EventoServiceImplTest {

    // Simula el repositorio de eventos
    @Mock
    private EventoRepository eventoRepository;

    // Inyecta el mock del repositorio en el servicio que se está probando
    @InjectMocks
    private EventoServiceImpl eventoService;

    private Evento evento1;
    private Evento evento2;

    @BeforeEach
    void setUp() {
        // Inicializa los mocks y configura los datos de prueba
        MockitoAnnotations.openMocks(this);
        evento1 = new Evento(1L, "Adopción de Mascotas", "Evento para promover la adopción responsable de mascotas", LocalDate.of(2023, 12, 15), "Parque Central");
        evento2 = new Evento(2L, "Educación sobre Tenencia Responsable", "Charla educativa sobre el cuidado y responsabilidad de tener mascotas", LocalDate.of(2024, 1, 20), "Centro de Convenciones");
    }

    @Test
    void testGetAllEventos() {
        // Simula que el repositorio devuelve una lista de eventos
        List<Evento> mockEventos = Arrays.asList(evento1, evento2);
        when(eventoRepository.findAll()).thenReturn(mockEventos);

        // Llama al método del servicio para obtener todos los eventos
        List<Evento> result = eventoService.getAllEventos();

        // Verifica que el resultado no sea nulo
        assertNotNull(result);
        // Verifica que la lista contiene exactamente 2 eventos
        assertEquals(2, result.size());
        // Verifica que el nombre del primer evento sea "Adopción de Mascotas"
        assertEquals("Adopción de Mascotas", result.get(0).getName());
        // Verifica que el método findAll() del repositorio fue llamado exactamente una vez
        verify(eventoRepository, times(1)).findAll();
    }

    @Test
    void testGetEventoById() {
        // Simula que el repositorio devuelve un evento específico al buscar por ID
        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento1));

        // Llama al método del servicio para obtener un evento por ID
        Evento result = eventoService.getEventoById(1L);

        // Verifica que el resultado no sea nulo
        assertNotNull(result);
        // Verifica que el nombre del evento sea "Adopción de Mascotas"
        assertEquals("Adopción de Mascotas", result.getName());
        // Verifica que el método findById(1L) del repositorio fue llamado exactamente una vez
        verify(eventoRepository, times(1)).findById(1L);
    }
}
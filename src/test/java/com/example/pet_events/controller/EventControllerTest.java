package com.example.pet_events.controller;

import com.example.pet_events.model.Evento;
import com.example.pet_events.service.EventoService;
import com.example.pet_events.service.ParticipanteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventoService eventoService;

    @MockBean
    private ParticipanteService participanteService;

    private Evento evento1;

    @BeforeEach
    void setUp() {
        // Configuración inicial: se crea un evento de prueba
        evento1 = new Evento(1L, "Adopción de Mascotas", "Evento para promover la adopción responsable de mascotas", LocalDate.of(2023, 12, 15), "Parque Central");
    }

    @Test
    void testGetAllEventos() throws Exception {
        // Simula que el servicio devuelve una lista de eventos
        List<Evento> mockEventos = Arrays.asList(evento1);
        when(eventoService.getAllEventos()).thenReturn(mockEventos);

        // Realiza una solicitud GET al endpoint /eventos
        mockMvc.perform(get("/eventos")
                .contentType(MediaType.APPLICATION_JSON))
                // Verifica que la respuesta sea HTTP 200 OK
                .andExpect(status().isOk())
                // Verifica que el primer evento en la respuesta tenga el nombre "Adopción de Mascotas"
                .andExpect(jsonPath("$[0].nombreEvento").value("Adopción de Mascotas"));

        // Verifica que el método getAllEventos() del servicio fue llamado exactamente una vez
        verify(eventoService, times(1)).getAllEventos();
    }

    @Test
    void testGetEventoById() throws Exception {
        // Simula que el servicio devuelve un evento específico al buscar por ID
        when(eventoService.getEventoById(1L)).thenReturn(evento1);

        // Realiza una solicitud GET al endpoint /eventos/1
        mockMvc.perform(get("/eventos/1")
                .contentType(MediaType.APPLICATION_JSON))
                // Verifica que la respuesta sea HTTP 200 OK
                .andExpect(status().isOk())
                // Verifica que el evento en la respuesta tenga el nombre "Adopción de Mascotas"
                .andExpect(jsonPath("$.nombreEvento").value("Adopción de Mascotas"));

        // Verifica que el método getEventoById(1L) del servicio fue llamado exactamente una vez
        verify(eventoService, times(1)).getEventoById(1L);
    }

}
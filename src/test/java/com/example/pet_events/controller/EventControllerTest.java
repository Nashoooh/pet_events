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
        evento1 = new Evento(1L, "Adopción de Mascotas", "Evento para promover la adopción responsable de mascotas", LocalDate.of(2023, 12, 15), "Parque Central");
    }

    @Test
    void testGetAllEventos() throws Exception {
        List<Evento> mockEventos = Arrays.asList(evento1);

        when(eventoService.getAllEventos()).thenReturn(mockEventos);

        mockMvc.perform(get("/eventos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreEvento").value("Adopción de Mascotas"));

        verify(eventoService, times(1)).getAllEventos();
    }

    @Test
    void testGetEventoById() throws Exception {
        when(eventoService.getEventoById(1L)).thenReturn(evento1);

        mockMvc.perform(get("/eventos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreEvento").value("Adopción de Mascotas"));

        verify(eventoService, times(1)).getEventoById(1L);
    }

}
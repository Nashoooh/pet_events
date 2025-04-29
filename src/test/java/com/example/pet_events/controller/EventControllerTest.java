package com.example.pet_events.controller;

import com.example.pet_events.model.Evento;
import com.example.pet_events.model.Participante;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
    private Participante participante1;

    @BeforeEach
    void setUp() {
        evento1 = new Evento(1L, "Adopción de Mascotas", "Evento para promover la adopción responsable de mascotas", LocalDate.of(2023, 12, 15), "Parque Central");
        participante1 = new Participante(1L, "Carlos López", "Rex", "Perro", 4, evento1);
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

    @Test
    void testCreateEvento() throws Exception {
        Evento newEvento = new Evento(null, "Feria de Adopción", "Feria para promover la adopción de mascotas", LocalDate.of(2024, 2, 10), "Casa de la Cultura");

        when(eventoService.createEvento(any(Evento.class))).thenReturn(newEvento);

        mockMvc.perform(post("/eventos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombreEvento\":\"Feria de Adopción\",\"descripcion\":\"Feria para promover la adopción de mascotas\",\"fecha\":\"2024-02-10\",\"ubicacion\":\"Casa de la Cultura\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreEvento").value("Feria de Adopción"));

        verify(eventoService, times(1)).createEvento(any(Evento.class));
    }

    @Test
    void testAddParticipanteToEvento() throws Exception {
        // Configurar mocks
        when(eventoService.getEventoById(1L)).thenReturn(evento1);
        when(participanteService.createParticipante(any(Participante.class))).thenReturn(participante1);
    
        // Realizar la solicitud POST
        mockMvc.perform(post("/eventos/1/participantes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"Carlos López\",\"nombreMascota\":\"Rex\",\"tipoMascota\":\"Perro\",\"edadMascota\":4}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreEvento").value("Adopción de Mascotas")) // Verificar el nombre del evento
                .andExpect(jsonPath("$.participantes[0].nombre").value("Carlos López")) // Verificar el nombre del participante
                .andExpect(jsonPath("$.participantes[0].nombreMascota").value("Rex")) // Verificar el nombre de la mascota
                .andExpect(jsonPath("$.participantes[0].tipoMascota").value("Perro")) // Verificar el tipo de mascota
                .andExpect(jsonPath("$.participantes[0].edadMascota").value(4)); // Verificar la edad de la mascota
    
        // Verificar interacciones con los servicios
        verify(eventoService, times(1)).getEventoById(1L);
        verify(participanteService, times(1)).createParticipante(any(Participante.class));
    }

    @Test
    void testUpdateParticipanteInEvento() throws Exception {
        Participante updatedParticipante = new Participante(1L, "Carlos López Actualizado", "Rex", "Perro", 5, evento1);

        when(participanteService.getParticipanteById(1L)).thenReturn(participante1);
        when(participanteService.updateParticipante(eq(1L), any(Participante.class))).thenReturn(updatedParticipante);

        mockMvc.perform(put("/eventos/1/participantes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"Carlos López Actualizado\",\"nombreMascota\":\"Rex\",\"tipoMascota\":\"Perro\",\"edadMascota\":5}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("El participante fue actualizado correctamente."));

        verify(participanteService, times(1)).getParticipanteById(1L);
        verify(participanteService, times(1)).updateParticipante(eq(1L), any(Participante.class));
    }

    @Test
    void testDeleteParticipanteFromEvento() throws Exception {
        when(participanteService.getParticipanteById(1L)).thenReturn(participante1);

        mockMvc.perform(delete("/eventos/1/participantes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("El participante fue eliminado correctamente."));

        verify(participanteService, times(1)).getParticipanteById(1L);
        verify(participanteService, times(1)).deleteParticipante(1L);
    }
}
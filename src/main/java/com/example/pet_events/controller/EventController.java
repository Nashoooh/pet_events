package com.example.pet_events.controller;

import com.example.pet_events.model.Evento;
import com.example.pet_events.model.Participante;
import com.example.pet_events.service.EventoService;
import com.example.pet_events.service.ParticipanteService;
import com.example.pet_events.dto.EventoConParticipantesDTO;
import com.example.pet_events.dto.ParticipanteDTO;
import com.example.pet_events.exception.ParticipantNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@RestController
@RequestMapping("/eventos")
public class EventController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ParticipanteService participanteService;

    // GET /eventos - Obtener todos los eventos
    @GetMapping
    public ResponseEntity<List<EntityModel<Evento>>> getAllEventos() {
        List<Evento> eventos = eventoService.getAllEventos();
    
        List<EntityModel<Evento>> eventoModels = eventos.stream()
                .map(evento -> {
                    EntityModel<Evento> model = EntityModel.of(evento);
                    Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(EventController.class).getEventoById(evento.getId())).withSelfRel();
                    model.add(selfLink);
                    return model;
                })
                .toList();
    
        return ResponseEntity.ok(eventoModels);
    }
    

    // POST /eventos - Crear un nuevo evento
    @PostMapping
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
        Evento nuevoEvento = eventoService.createEvento(evento);
        return ResponseEntity.ok(nuevoEvento);
    }

    // GET /eventos/{id} - Obtener un evento por ID
    @GetMapping("/{idEvento}")
    public ResponseEntity<EntityModel<Evento>> getEventoById(@PathVariable Long idEvento) {
        Evento evento = eventoService.getEventoById(idEvento);
    
        EntityModel<Evento> model = EntityModel.of(evento);
        Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(EventController.class).getEventoById(idEvento)).withSelfRel();
        Link allEventosLink = WebMvcLinkBuilder.linkTo(methodOn(EventController.class).getAllEventos()).withRel("eventos");
        model.add(selfLink, allEventosLink);
    
        return ResponseEntity.ok(model);
    }
    

    // POST /eventos/{eventoId}/participantes - Agregar un participante a un evento
    @PostMapping("/{eventoId}/participantes")
    public ResponseEntity<EntityModel<EventoConParticipantesDTO>> addParticipanteToEvento(
            @PathVariable Long eventoId, @RequestBody Participante participante) {
    
        Evento evento = eventoService.getEventoById(eventoId);
        participante.setEvento(evento);
        participanteService.createParticipante(participante);
        List<Participante> participantes = participanteService.getParticipantByEvent(eventoId);
    
        List<ParticipanteDTO> participantesDTO = participantes.stream()
                .map(p -> new ParticipanteDTO(p.getId(), p.getName(), p.getPetName(), p.getPetType(), p.getPetAge()))
                .toList();
    
        EventoConParticipantesDTO dto = new EventoConParticipantesDTO(
                evento.getId(), evento.getName(), evento.getDate(), evento.getLocation(), evento.getDescription(), participantesDTO);
    
        EntityModel<EventoConParticipantesDTO> model = EntityModel.of(dto);
        model.add(WebMvcLinkBuilder.linkTo(methodOn(EventController.class).getEventoById(eventoId)).withRel("evento"));
        model.add(WebMvcLinkBuilder.linkTo(methodOn(EventController.class).getParticipantesByEventoId(eventoId)).withRel("participantes"));
    
        return ResponseEntity.ok(model);
    }
    

    // GET /eventos/{eventoId}/participantes - Obtener todos los participantes de un evento
    @GetMapping("/{eventoId}/participantes")
    public ResponseEntity<List<ParticipanteDTO>> getParticipantesByEventoId(@PathVariable Long eventoId) {
        List<Participante> participantes = participanteService.getParticipantByEvent(eventoId);

        // Convertir los participantes a DTOs
        List<ParticipanteDTO> participantesDTO = participantes.stream()
                .map(p -> new ParticipanteDTO(p.getId(), p.getName(), p.getPetName(), p.getPetType(),p.getPetAge()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(participantesDTO);
    }

    // DELETE /eventos/{eventoId}/participantes/{participanteId} - Eliminar un participante de un evento
    @DeleteMapping("/{eventoId}/participantes/{participanteId}")
    public ResponseEntity<Map<String, Object>> deleteParticipanteFromEvento(
            @PathVariable Long eventoId,
            @PathVariable Long participanteId) {
        try {
            // Obtener el participante por ID
            Participante participante = participanteService.getParticipanteById(participanteId);

            // Validar que el participante pertenece al evento especificado
            if (!participante.getEvento().getId().equals(eventoId)) {
                return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "El participante no pertenece al evento especificado."
                ));
            }

            // Eliminar el participante
            participanteService.deleteParticipante(participanteId);

            // Retornar un JSON con éxito
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "El participante fue eliminado correctamente."
            ));
        } catch (ParticipantNotFoundException e) {
            // Manejar el caso en que el participante no exista
            return ResponseEntity.status(404).body(Map.of(
                "success", false,
                "message", e.getMessage()
            ));
        } catch (Exception e) {
            // Manejar cualquier otro error inesperado
            return ResponseEntity.status(500).body(Map.of(
                "success", false,
                "message", "Ocurrió un error inesperado al eliminar el participante."
            ));
        }
    }

    // PUT /eventos/{eventoId}/participantes/{participanteId} - Actualizar un participante de un evento
    @PutMapping("/{eventoId}/participantes/{participanteId}")
    public ResponseEntity<Map<String, Object>> updateParticipanteInEvento(
            @PathVariable Long eventoId,
            @PathVariable Long participanteId,
            @RequestBody Participante participanteActualizado) {
        try {
            // Obtener el participante por ID
            Participante participanteExistente = participanteService.getParticipanteById(participanteId);

            // Validar que el participante pertenece al evento especificado
            if (!participanteExistente.getEvento().getId().equals(eventoId)) {
                return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "El participante no pertenece al evento especificado."
                ));
            }

            // Actualizar el participante utilizando el método del servicio
            participanteService.updateParticipante(participanteId, participanteActualizado);

            // Retornar un JSON con éxito
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "El participante fue actualizado correctamente."
            ));
        } catch (ParticipantNotFoundException e) {
            // Manejar el caso en que el participante no exista
            return ResponseEntity.status(404).body(Map.of(
                "success", false,
                "message", e.getMessage()
            ));
        } catch (Exception e) {
            // Manejar cualquier otro error inesperado
            return ResponseEntity.status(500).body(Map.of(
                "success", false,
                "message", "Ocurrió un error inesperado al actualizar el participante."
            ));
        }
    }

}

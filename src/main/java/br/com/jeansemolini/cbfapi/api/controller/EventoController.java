package br.com.jeansemolini.cbfapi.api.controller;

import br.com.jeansemolini.cbfapi.api.model.dto.EventoDTO;
import br.com.jeansemolini.cbfapi.api.model.dto.EventoPartidaDTO;
import br.com.jeansemolini.cbfapi.api.model.dto.PartidaDTO;
import br.com.jeansemolini.cbfapi.domain.model.Evento;
import br.com.jeansemolini.cbfapi.domain.model.Partida;
import br.com.jeansemolini.cbfapi.domain.service.EventoService;
import br.com.jeansemolini.cbfapi.domain.service.PartidaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/eventos")
public class EventoController {

    private EventoService eventoService;
    private PartidaService partidaService;
    private ModelMapper modelMapper;

    @GetMapping("partidas/{partidaId}")
    public ResponseEntity<EventoPartidaDTO> listarEventosPartida(@PathVariable UUID partidaId) {
        Partida partida = partidaService.buscarPorId(partidaId);
        List<Evento> eventos = eventoService.listarPorPartida(partidaId);

        List<EventoDTO> eventosDTO = eventos.stream().map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());

        EventoPartidaDTO eventosPartida = new EventoPartidaDTO();
        eventosPartida.setPartida(modelMapper.map(partida, PartidaDTO.class));
        eventosPartida.setEventos(eventosDTO);

        return ResponseEntity.ok(eventosPartida);
    }
}

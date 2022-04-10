package br.com.jeansemolini.cbfapi.api.controller;

import br.com.jeansemolini.cbfapi.api.model.EventoInput;
import br.com.jeansemolini.cbfapi.api.model.PartidaInput;
import br.com.jeansemolini.cbfapi.api.model.TimeTorneioInput;
import br.com.jeansemolini.cbfapi.api.model.TorneioInput;
import br.com.jeansemolini.cbfapi.api.model.dto.EventoPartidaDTO;
import br.com.jeansemolini.cbfapi.api.model.dto.PartidaDTO;
import br.com.jeansemolini.cbfapi.api.model.dto.TimeDTO;
import br.com.jeansemolini.cbfapi.api.model.dto.TorneioDTO;
import br.com.jeansemolini.cbfapi.domain.exception.RegraNegocioException;
import br.com.jeansemolini.cbfapi.domain.model.Evento;
import br.com.jeansemolini.cbfapi.domain.model.Partida;
import br.com.jeansemolini.cbfapi.domain.model.Time;
import br.com.jeansemolini.cbfapi.domain.model.Torneio;
import br.com.jeansemolini.cbfapi.domain.service.PartidaService;
import br.com.jeansemolini.cbfapi.domain.service.TimeService;
import br.com.jeansemolini.cbfapi.domain.service.TorneioService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/torneios")
public class TorneioController {

    private TorneioService torneioService;
    private TimeService timeService;
    private PartidaService partidaService;
    private JmsTemplate jmsTemplate;
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TorneioDTO salvar(@RequestBody @Valid TorneioInput torneioInput) {
        Torneio torneio = torneioService.salvar(modelMapper.map(torneioInput, Torneio.class));
        return modelMapper.map(torneio, TorneioDTO.class);
    }

    @PostMapping("/{torneioId}/times")
    @ResponseStatus(HttpStatus.CREATED)
    public TorneioDTO adicionarTime(@RequestBody @Valid TimeTorneioInput timeTorneioInput, @PathVariable UUID torneioId) {
        Torneio torneio = torneioService.buscarPorId(torneioId);
        Time time = timeService.buscarPorId(timeTorneioInput.getTime());
        if (torneio.getTimes().contains(time)) {
            throw new RegraNegocioException("Time já cadastrado no torneio");
        }
        torneio.getTimes().add(time);
        return modelMapper.map(torneioService.salvar(torneio), TorneioDTO.class);
    }

    @PostMapping("/{torneioId}/partidas")
    @ResponseStatus(HttpStatus.CREATED)
    public PartidaDTO marcarPartida(@RequestBody @Valid PartidaInput partidaInput, @PathVariable UUID torneioId) {
        Torneio torneio = torneioService.buscarPorId(torneioId);
        Time timeMandante = timeService.buscarPorId(partidaInput.getTimeMandante());
        Time timeVisitante = timeService.buscarPorId(partidaInput.getTimeVisitante());
        Partida partida = modelMapper.map(partidaInput, Partida.class);
        partida.setTimeMandante(timeMandante);
        partida.setTimeVisitante(timeVisitante);
        partida.setTorneio(torneio);
        return modelMapper.map(partidaService.salvar(partida), PartidaDTO.class);
    }

    @GetMapping("/{torneioId}/partidas")
    public ResponseEntity<List<PartidaDTO>> partidasPorTorneio(@PathVariable UUID torneioId) {
        List<PartidaDTO> partidas = partidaService.listarPorTorneio(torneioId).stream()
                .map(partida -> modelMapper.map(partida, PartidaDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(partidas);
    }

    @PostMapping("/{torneioId}/partidas/{partidaId}/eventos/inicio")
    public ResponseEntity<Void> iniciarPartida(@PathVariable UUID torneioId, @PathVariable UUID partidaId) {
        EventoInput evento = montarEvento(torneioId, partidaId, "iniciar-partida");
        jmsTemplate.convertAndSend("eventos-partida", evento);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{torneioId}/partidas/{partidaId}/eventos/gol")
    public ResponseEntity<Void> registrarGol(@PathVariable UUID torneioId, @PathVariable UUID partidaId) {
        EventoInput evento = montarEvento(torneioId, partidaId, "gol");
        jmsTemplate.convertAndSend("eventos-partida", evento);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{torneioId}/partidas/{partidaId}/eventos/intervalo")
    public ResponseEntity<Void> intervaloPartida(@PathVariable UUID torneioId, @PathVariable UUID partidaId) {
        EventoInput evento = montarEvento(torneioId, partidaId, "intervalo");
        jmsTemplate.convertAndSend("eventos-partida", evento);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{torneioId}/partidas/{partidaId}/eventos/acrescimo")
    public ResponseEntity<Void> acrescimoPartida(@PathVariable UUID torneioId, @PathVariable UUID partidaId) {
        EventoInput evento = montarEvento(torneioId, partidaId, "acrescimo");
        jmsTemplate.convertAndSend("eventos-partida", evento);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{torneioId}/partidas/{partidaId}/eventos/substituicao ")
    public ResponseEntity<Void> substituicaoJogador(@PathVariable UUID torneioId, @PathVariable UUID partidaId) {
        EventoInput evento = montarEvento(torneioId, partidaId, "substituicao ");
        jmsTemplate.convertAndSend("eventos-partida", evento);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{torneioId}/partidas/{partidaId}/eventos/advertencia")
    public ResponseEntity<Void> registrarAdvertencia(@PathVariable UUID torneioId, @PathVariable UUID partidaId) {
        EventoInput evento = montarEvento(torneioId, partidaId, "advertencia");
        jmsTemplate.convertAndSend("eventos-partida", evento);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{torneioId}/partidas/{partidaId}/eventos/fim ")
    public ResponseEntity<Void> finalizarPartida(@PathVariable UUID torneioId, @PathVariable UUID partidaId) {
        EventoInput evento = montarEvento(torneioId, partidaId, "fim ");
        jmsTemplate.convertAndSend("eventos-partida", evento);
        return ResponseEntity.noContent().build();
    }

    private EventoInput montarEvento(UUID torneioId, UUID partidaId, String tipoEvento) {
        torneioService.buscarPorId(torneioId);
        partidaService.buscarPorId(partidaId);
        EventoInput evento = new EventoInput();
        evento.setPartidaId(partidaId);
        evento.setTipoEvento(tipoEvento);
        return evento;
    }
}

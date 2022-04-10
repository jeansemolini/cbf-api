package br.com.jeansemolini.cbfapi.api.controller;

import br.com.jeansemolini.cbfapi.api.model.TransferenciaInput;
import br.com.jeansemolini.cbfapi.api.model.dto.TransferenciaDTO;
import br.com.jeansemolini.cbfapi.domain.model.Jogador;
import br.com.jeansemolini.cbfapi.domain.model.Time;
import br.com.jeansemolini.cbfapi.domain.model.Transferencia;
import br.com.jeansemolini.cbfapi.domain.service.JogadorService;
import br.com.jeansemolini.cbfapi.domain.service.TimeService;
import br.com.jeansemolini.cbfapi.domain.service.TransferenciaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private TransferenciaService transferenciaService;
    private JogadorService jogadorService;
    private TimeService timeService;
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransferenciaDTO salvar(@RequestBody @Valid TransferenciaInput transferenciaInput) {
        Time timeOrigem = timeService.buscarPorId(transferenciaInput.getTimeOrigem());
        Time timeDestino = timeService.buscarPorId(transferenciaInput.getTimeDestino());
        Jogador jogador = jogadorService.buscarPorId(transferenciaInput.getJogadorId());
        Transferencia transferencia = modelMapper.map(transferenciaInput, Transferencia.class);
        transferencia.setTimeOrigem(timeOrigem);
        transferencia.setTimeDestino(timeDestino);
        transferencia.setJogador(jogador);

        return modelMapper.map(transferenciaService.salvar(transferencia), TransferenciaDTO.class);
    }

//    @GetMapping
//    public ResponseEntity<List<JogadorDTO>> listar() {
//
//        List<JogadorDTO> times = jogadorService.listarTodos().stream()
//                .map(time -> modelMapper.map(time, TimeDTO.class))
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(times);
//    }
}

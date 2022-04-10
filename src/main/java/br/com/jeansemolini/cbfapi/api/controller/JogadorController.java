package br.com.jeansemolini.cbfapi.api.controller;

import br.com.jeansemolini.cbfapi.api.model.JogadorInput;
import br.com.jeansemolini.cbfapi.api.model.TimeInput;
import br.com.jeansemolini.cbfapi.api.model.dto.JogadorDTO;
import br.com.jeansemolini.cbfapi.api.model.dto.TimeDTO;
import br.com.jeansemolini.cbfapi.domain.model.Jogador;
import br.com.jeansemolini.cbfapi.domain.model.Time;
import br.com.jeansemolini.cbfapi.domain.service.JogadorService;
import br.com.jeansemolini.cbfapi.domain.service.TimeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/jogadores")
public class JogadorController {

    private JogadorService jogadorService;
    private TimeService timeService;
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JogadorDTO salvar(@RequestBody @Valid JogadorInput jogadorInput) {
        Time time = timeService.buscarPorId(jogadorInput.getTimeId());
        Jogador jogador = jogadorService.salvar(modelMapper.map(jogadorInput, Jogador.class));
        jogador.setTime(time);
        return modelMapper.map(jogador, JogadorDTO.class);
    }
}

package br.com.jeansemolini.cbfapi.api.controller;

import br.com.jeansemolini.cbfapi.api.model.TimeInput;
import br.com.jeansemolini.cbfapi.api.model.dto.TimeDTO;
import br.com.jeansemolini.cbfapi.domain.model.Time;
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
@RequestMapping("/times")
public class TimeController {

    private TimeService timeService;
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TimeDTO salvar(@RequestBody @Valid TimeInput timeInput) {
        Time time = timeService.salvar(modelMapper.map(timeInput, Time.class));
        return modelMapper.map(time, TimeDTO.class);
    }

    @GetMapping
    public ResponseEntity<List<TimeDTO>> listar() {

        List<TimeDTO> times = timeService.listarTodos().stream()
                .map(time -> modelMapper.map(time, TimeDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(times);
    }
}

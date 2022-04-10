package br.com.jeansemolini.cbfapi.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class PartidaDTO {

    private UUID id;
    private TimePartidaDTO timeMandante;
    private TimePartidaDTO timeVisitante;
    private TorneioPartidaDTO torneio;
    private LocalDateTime dataHora;

}

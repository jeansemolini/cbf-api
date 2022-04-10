package br.com.jeansemolini.cbfapi.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class EventoDTO {

    private String tipoEvento;
    private LocalDateTime dataHora;
}

package br.com.jeansemolini.cbfapi.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class EventoPartidaDTO {

    private PartidaDTO partida;
    private List<EventoDTO> eventos;
}

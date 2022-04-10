package br.com.jeansemolini.cbfapi.api.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class EventoInput {

    private String tipoEvento;
    private UUID partidaId;
}

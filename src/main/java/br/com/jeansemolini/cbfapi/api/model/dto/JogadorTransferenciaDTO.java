package br.com.jeansemolini.cbfapi.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class JogadorTransferenciaDTO {

    private UUID id;
    private String nome;
}

package br.com.jeansemolini.cbfapi.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter @Setter
public class JogadorDTO {

    private UUID id;
    private String nome;
    private Date dataNascimento;
    private String pais;
    private TimeDTO time;
}

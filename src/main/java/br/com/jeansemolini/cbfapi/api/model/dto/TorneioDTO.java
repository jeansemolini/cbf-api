package br.com.jeansemolini.cbfapi.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter @Setter
public class TorneioDTO {

    private UUID id;
    private String nome;
    private Integer ano;
    private List<TimeDTO> times;
}

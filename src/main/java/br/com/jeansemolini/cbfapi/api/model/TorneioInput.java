package br.com.jeansemolini.cbfapi.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class TorneioInput {

    @Schema(description = "Nome do torneio")
    @NotNull
    private String nome;

    @Schema(description = "Ano do torneio")
    @NotNull
    private Integer ano;
}

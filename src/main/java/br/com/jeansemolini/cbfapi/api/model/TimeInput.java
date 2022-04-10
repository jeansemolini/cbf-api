package br.com.jeansemolini.cbfapi.api.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class TimeInput {

    @Schema(description = "Nome do time")
    @NotBlank(message = "Informar nome do time")
    private String nome;

    @Schema(description = "Sigla UF de origem do time")
    @NotBlank(message = "Informar UF do time")
    private String uf;
}

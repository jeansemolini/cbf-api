package br.com.jeansemolini.cbfapi.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Getter
public class JogadorInput {

    @Schema(description = "Nome do jogador")
    @NotBlank
    private String nome;

    @Schema(description = "Data de nascimento do jogador")
    @NotNull
    private Date dataNascimento;

    @Schema(description = "País de origem do jogador")
    @NotBlank
    private String pais;

    @Schema(description = "Código do time inicial do jogador")
    @NotNull
    private UUID timeId;
}

package br.com.jeansemolini.cbfapi.api.model;

import br.com.jeansemolini.cbfapi.domain.model.Jogador;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class TransferenciaInput {

    @NotNull
    private UUID timeOrigem;

    @NotNull
    private UUID timeDestino;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private UUID jogadorId;
}

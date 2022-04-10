package br.com.jeansemolini.cbfapi.api.model.dto;

import br.com.jeansemolini.cbfapi.domain.model.Time;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TransferenciaDTO {

    private UUID id;
    private Time timeOrigem;
    private Time timeDestino;
    private LocalDateTime data;
    private BigDecimal valor;
    private JogadorTransferenciaDTO jogador;
}

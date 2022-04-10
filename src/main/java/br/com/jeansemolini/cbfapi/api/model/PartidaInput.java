package br.com.jeansemolini.cbfapi.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class PartidaInput {

    @Schema(description = "Código do time mandante")
    @NotNull
    private UUID timeMandante;

    @Schema(description = "Código do time visitante")
    @NotNull
    private UUID timeVisitante;

    @Schema(description = "Data e hora da partida")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private LocalDateTime dataHora;
}

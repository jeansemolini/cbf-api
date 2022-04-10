package br.com.jeansemolini.cbfapi.api.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class TimeTorneioInput {

    @Schema(description = "Código do time")
    @NotNull
    private UUID time;
}

package br.com.jeansemolini.cbfapi.domain.service;

import br.com.jeansemolini.cbfapi.domain.model.Partida;

import java.util.List;
import java.util.UUID;

public interface PartidaService {
    Partida salvar(Partida partida);
    List<Partida> listarPorTorneio(UUID torneioId);
    Partida buscarPorId(UUID id);
}

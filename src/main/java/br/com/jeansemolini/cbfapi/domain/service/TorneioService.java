package br.com.jeansemolini.cbfapi.domain.service;

import br.com.jeansemolini.cbfapi.domain.model.Torneio;

import java.util.UUID;

public interface TorneioService {
    Torneio salvar(Torneio torneio);
    Torneio buscarPorId(UUID id);
}

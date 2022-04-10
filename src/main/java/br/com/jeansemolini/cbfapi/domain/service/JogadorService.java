package br.com.jeansemolini.cbfapi.domain.service;

import br.com.jeansemolini.cbfapi.domain.model.Jogador;

import java.util.UUID;

public interface JogadorService {

    Jogador salvar(Jogador jogador);
    Jogador buscarPorId(UUID id);
}

package br.com.jeansemolini.cbfapi.domain.service;

import br.com.jeansemolini.cbfapi.domain.model.Evento;

import java.util.List;
import java.util.UUID;

public interface EventoService {

    Evento salvar(Evento evento);
    List<Evento> listarPorPartida(UUID partidaId);
}

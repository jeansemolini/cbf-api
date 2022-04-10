package br.com.jeansemolini.cbfapi.domain.service;

import br.com.jeansemolini.cbfapi.domain.model.Time;

import java.util.List;
import java.util.UUID;

public interface TimeService {

    Time salvar(Time time);
    List<Time> listarTodos();
    Time buscarPorId(UUID id);
}

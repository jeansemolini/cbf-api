package br.com.jeansemolini.cbfapi.domain.service.impl;

import br.com.jeansemolini.cbfapi.domain.model.Evento;
import br.com.jeansemolini.cbfapi.domain.repository.EventoRepository;
import br.com.jeansemolini.cbfapi.domain.service.EventoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class EventoServiceImpl implements EventoService {

    private EventoRepository eventoRepository;

    @Override
    public Evento salvar(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Override
    public List<Evento> listarPorPartida(UUID partidaId) {
        return eventoRepository.findByPartidaId(partidaId);
    }
}

package br.com.jeansemolini.cbfapi.domain.service.listener;

import br.com.jeansemolini.cbfapi.api.model.EventoInput;
import br.com.jeansemolini.cbfapi.domain.model.Evento;
import br.com.jeansemolini.cbfapi.domain.service.EventoService;
import br.com.jeansemolini.cbfapi.domain.service.PartidaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EventoListener {

    private EventoService eventoService;
    private PartidaService partidaService;
    private ModelMapper modelMapper;

    @JmsListener(destination = "eventos-partida", containerFactory = "defaultFactory")
    public void inicioPartida(EventoInput eventoInput) {
        Evento evento = modelMapper.map(eventoInput, Evento.class);
        evento.setPartida(partidaService.buscarPorId(eventoInput.getPartidaId()));
        eventoService.salvar(evento);
    }
}

package br.com.jeansemolini.cbfapi.domain.service.impl;

import br.com.jeansemolini.cbfapi.domain.exception.RegistroNaoEncontradoException;
import br.com.jeansemolini.cbfapi.domain.model.Torneio;
import br.com.jeansemolini.cbfapi.domain.repository.TorneioRepository;
import br.com.jeansemolini.cbfapi.domain.service.TorneioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class TorneioServiceImpl implements TorneioService {

    private TorneioRepository torneioRepository;

    @Override
    public Torneio salvar(Torneio torneio) {
        return torneioRepository.save(torneio);
    }

    @Override
    public Torneio buscarPorId(UUID id) {
        return torneioRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(String.format("Torneio com id: %s não encontrado", id)));
    }
}

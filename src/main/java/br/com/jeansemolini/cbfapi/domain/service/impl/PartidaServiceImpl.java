package br.com.jeansemolini.cbfapi.domain.service.impl;

import br.com.jeansemolini.cbfapi.domain.exception.RegistroNaoEncontradoException;
import br.com.jeansemolini.cbfapi.domain.exception.RegraNegocioException;
import br.com.jeansemolini.cbfapi.domain.model.Partida;
import br.com.jeansemolini.cbfapi.domain.repository.PartidaRepository;
import br.com.jeansemolini.cbfapi.domain.service.PartidaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class PartidaServiceImpl implements PartidaService {

    private PartidaRepository partidaRepository;

    @Override
    public Partida salvar(Partida partida) {
        if (partida.getTimeMandante().equals(partida.getTimeVisitante())) {
            throw new RegraNegocioException("Partida deve ocorrer com times diferentes.");
        }
        return partidaRepository.save(partida);
    }

    @Override
    public List<Partida> listarPorTorneio(UUID torneioId) {
        return partidaRepository.findByTorneioId(torneioId);
    }

    @Override
    public Partida buscarPorId(UUID id) {
        return partidaRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(String.format("Partida com id: %s não encontrado", id)));
    }
}

package br.com.jeansemolini.cbfapi.domain.service.impl;

import br.com.jeansemolini.cbfapi.domain.exception.RegistroNaoEncontradoException;
import br.com.jeansemolini.cbfapi.domain.model.Jogador;
import br.com.jeansemolini.cbfapi.domain.repository.JogadorRepository;
import br.com.jeansemolini.cbfapi.domain.service.JogadorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class JogadorServiceImpl implements JogadorService {

    private JogadorRepository jogadorRepository;


    @Override
    public Jogador salvar(Jogador jogador) {
        return jogadorRepository.save(jogador);
    }

    @Override
    public Jogador buscarPorId(UUID id) {
        return jogadorRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(String.format("Jogador com id: %s não encontrado", id)));
    }
}

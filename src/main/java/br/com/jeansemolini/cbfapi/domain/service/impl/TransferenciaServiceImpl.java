package br.com.jeansemolini.cbfapi.domain.service.impl;

import br.com.jeansemolini.cbfapi.domain.exception.RegraNegocioException;
import br.com.jeansemolini.cbfapi.domain.model.Jogador;
import br.com.jeansemolini.cbfapi.domain.model.Transferencia;
import br.com.jeansemolini.cbfapi.domain.repository.TransferenciaRepository;
import br.com.jeansemolini.cbfapi.domain.service.JogadorService;
import br.com.jeansemolini.cbfapi.domain.service.TransferenciaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TransferenciaServiceImpl implements TransferenciaService {

    private TransferenciaRepository transferenciaRepository;
    private JogadorService jogadorService;

    @Override
    public Transferencia salvar(Transferencia transferencia) {
        if (transferencia.getTimeDestino().equals(transferencia.getJogador().getTime())) {
            throw new RegraNegocioException("Time atual igual ao time de destino.");
        }
        if (transferencia.getTimeOrigem().equals(transferencia.getTimeDestino())) {
            throw new RegraNegocioException("Transferência não pode ocorrer para o mesmo time.");
        }

        Transferencia transferenciaOk = transferenciaRepository.save(transferencia);
        Jogador jogador = transferencia.getJogador();
        jogador.setTime(transferencia.getTimeDestino());
        jogadorService.salvar(jogador);
        return transferenciaOk;
    }
}

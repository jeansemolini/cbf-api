package br.com.jeansemolini.cbfapi.domain.repository;

import br.com.jeansemolini.cbfapi.domain.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JogadorRepository extends JpaRepository<Jogador, UUID> {
}

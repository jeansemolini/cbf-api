package br.com.jeansemolini.cbfapi.domain.repository;

import br.com.jeansemolini.cbfapi.domain.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PartidaRepository extends JpaRepository<Partida, UUID> {
    List<Partida> findByTorneioId(UUID torneioId);
}

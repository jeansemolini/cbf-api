package br.com.jeansemolini.cbfapi.domain.repository;

import br.com.jeansemolini.cbfapi.domain.model.Torneio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TorneioRepository extends JpaRepository<Torneio, UUID> {
}

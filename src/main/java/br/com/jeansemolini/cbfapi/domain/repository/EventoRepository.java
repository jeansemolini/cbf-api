package br.com.jeansemolini.cbfapi.domain.repository;

import br.com.jeansemolini.cbfapi.domain.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventoRepository extends JpaRepository<Evento, UUID> {
    List<Evento> findByPartidaId(UUID partidaId);
}

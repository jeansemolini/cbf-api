package br.com.jeansemolini.cbfapi.domain.repository;

import br.com.jeansemolini.cbfapi.domain.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferenciaRepository extends JpaRepository<Transferencia, UUID> {
}

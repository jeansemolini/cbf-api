package br.com.jeansemolini.cbfapi.domain.repository;

import br.com.jeansemolini.cbfapi.domain.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TimeRepository extends JpaRepository<Time, UUID> {

    Optional<Time> findByNomeAndUf(String nome, String uf);

}

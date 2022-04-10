package br.com.jeansemolini.cbfapi.domain.service.impl;

import br.com.jeansemolini.cbfapi.domain.exception.RegistroJaExisteException;
import br.com.jeansemolini.cbfapi.domain.exception.RegistroNaoEncontradoException;
import br.com.jeansemolini.cbfapi.domain.model.Time;
import br.com.jeansemolini.cbfapi.domain.repository.TimeRepository;
import br.com.jeansemolini.cbfapi.domain.service.TimeService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class TimeServiceImpl implements TimeService {

    private TimeRepository timeRepository;

    @CacheEvict(value="times", allEntries=true)
    @Override
    public Time salvar(Time time) {
        Optional<Time> byNomeAndUf = timeRepository.findByNomeAndUf(time.getNome(), time.getUf());
        if (byNomeAndUf.isPresent()) {
            throw new RegistroJaExisteException("Time já cadastrado.");
        }
        return timeRepository.save(time);
    }

    @Cacheable(value = "times")
    @Override
    public List<Time> listarTodos() {
        return timeRepository.findAll();
    }

    @Override
    public Time buscarPorId(UUID id) {
        return timeRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(String.format("Time com id: %s não encontrado", id)));
    }
}

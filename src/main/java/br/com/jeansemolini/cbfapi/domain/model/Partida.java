package br.com.jeansemolini.cbfapi.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Getter
@Setter
public class Partida {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @OneToOne
    @JoinColumn(nullable = false)
    private Time timeMandante;

    @OneToOne
    @JoinColumn(nullable = false)
    private Time timeVisitante;

    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Torneio torneio;

    @OneToMany(mappedBy = "partida")
    private List<Evento> eventos;
}

package com.guilherme.projectportfolioapi.entity;

import com.guilherme.projectportfolioapi.enums.StatusProjeto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "projetos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate previsaoTermino;

    private LocalDate dataRealTermino;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal orcamentoTotal;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusProjeto status;

    @ManyToOne
    @JoinColumn(name = "gerente_id")
    private Membro gerente;

    @ManyToMany
    @JoinTable(
            name = "projeto_membros",
            joinColumns = @JoinColumn(name = "projeto_id"),
            inverseJoinColumns = @JoinColumn(name = "membro_id")
    )
    private List<Membro> membros;
}
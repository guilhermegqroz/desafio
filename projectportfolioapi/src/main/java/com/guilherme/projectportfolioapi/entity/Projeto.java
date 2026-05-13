package com.guilherme.projectportfolioapi.entity;

import com.guilherme.projectportfolioapi.enums.ClassificacaoRisco;
import com.guilherme.projectportfolioapi.enums.StatusProjeto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "projetos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private LocalDate dataInicio;

    private LocalDate previsaoTermino;

    private LocalDate dataRealTermino;

    private BigDecimal orcamentoTotal;

    @Column(length = 2000)
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusProjeto status;

    @Enumerated(EnumType.STRING)
    private ClassificacaoRisco classificacaoRisco;

    @ManyToOne
    @JoinColumn(name = "gerente_id")
    private Membro gerenteResponsavel;

    @ManyToMany
    @JoinTable(
            name = "projeto_membros",
            joinColumns = @JoinColumn(name = "projeto_id"),
            inverseJoinColumns = @JoinColumn(name = "membro_id")
    )
    private List<Membro> membros;

}
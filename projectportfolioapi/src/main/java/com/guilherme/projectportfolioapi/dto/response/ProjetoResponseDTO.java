package com.guilherme.projectportfolioapi.dto.response;

import com.guilherme.projectportfolioapi.enums.ClassificacaoRisco;
import com.guilherme.projectportfolioapi.enums.StatusProjeto;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjetoResponseDTO {

    private Long id;

    private String nome;

    private LocalDate dataInicio;

    private LocalDate previsaoTermino;

    private LocalDate dataRealTermino;

    private BigDecimal orcamentoTotal;

    private String descricao;

    private String gerente;

    private StatusProjeto status;

    private ClassificacaoRisco risco;
}
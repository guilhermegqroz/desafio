package com.guilherme.projectportfolioapi.dto.request;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjetoRequestDTO {

    private String nome;

    private LocalDate dataInicio;

    private LocalDate previsaoTermino;

    private LocalDate dataRealTermino;

    private BigDecimal orcamentoTotal;

    private String descricao;

    private Long gerenteId;
}
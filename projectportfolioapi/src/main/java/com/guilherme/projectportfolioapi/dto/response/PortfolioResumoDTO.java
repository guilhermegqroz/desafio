package com.guilherme.projectportfolioapi.dto.response;

import com.guilherme.projectportfolioapi.enums.StatusProjeto;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioResumoDTO {

    private Map<StatusProjeto, Long>
            quantidadeProjetosPorStatus;

    private Map<StatusProjeto, BigDecimal>
            totalOrcadoPorStatus;

    private Double mediaDuracaoProjetosEncerrados;

    private Long totalMembrosUnicosAlocados;
}
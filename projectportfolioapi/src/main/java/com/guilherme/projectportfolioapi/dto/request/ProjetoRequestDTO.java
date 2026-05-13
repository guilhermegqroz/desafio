package com.guilherme.projectportfolioapi.dto.request;

import com.guilherme.projectportfolioapi.enums.ClassificacaoRisco;
import com.guilherme.projectportfolioapi.enums.StatusProjeto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class ProjetoRequestDTO {

    @NotBlank
    private String nome;
    @NotNull
    private LocalDate dataInicio;
    @NotNull
    private LocalDate previsaoTermino;

    @NotNull
    private BigDecimal orcamentoTotal;
    private String descricao;
    @NotNull
    private StatusProjeto status;
    @NotNull
    private ClassificacaoRisco classificacaoRisco;
    private UUID gerenteId;

}
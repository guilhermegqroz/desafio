package com.guilherme.projectportfolioapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MembroRequestDTO {
    @NotBlank
    private String nome;
    @NotBlank
    private String atribuicao;
}
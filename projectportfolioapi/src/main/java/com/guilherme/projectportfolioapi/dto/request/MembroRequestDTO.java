package com.guilherme.projectportfolioapi.dto.request;

import com.guilherme.projectportfolioapi.enums.Atribuicao;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MembroRequestDTO {

    @NotBlank
    private String nome;
    
    @NotBlank
    private Atribuicao atribuicao;
}
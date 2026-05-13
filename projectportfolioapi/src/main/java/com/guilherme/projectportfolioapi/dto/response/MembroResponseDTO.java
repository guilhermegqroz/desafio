package com.guilherme.projectportfolioapi.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class MembroResponseDTO {
    private UUID id;
    private String nome;
    private String atribuicao;
}
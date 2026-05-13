package com.guilherme.projectportfolioapi.dto.response;

import com.guilherme.projectportfolioapi.enums.Atribuicao;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MembroResponseDTO {
    private Long id;
    private String nome;
    private Atribuicao atribuicao;
}
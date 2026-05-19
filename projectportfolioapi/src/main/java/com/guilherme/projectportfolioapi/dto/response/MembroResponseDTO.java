package com.guilherme.projectportfolioapi.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MembroResponseDTO {

    private String id;
    private String nome;
    private String atribuicao;

}
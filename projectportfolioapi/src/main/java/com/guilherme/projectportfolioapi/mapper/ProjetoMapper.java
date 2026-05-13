package com.guilherme.projectportfolioapi.mapper;

import com.guilherme.projectportfolioapi.dto.request.ProjetoRequestDTO;
import com.guilherme.projectportfolioapi.dto.response.ProjetoResponseDTO;
import com.guilherme.projectportfolioapi.entity.Membro;
import com.guilherme.projectportfolioapi.entity.Projeto;
import org.springframework.stereotype.Component;

@Component
public class ProjetoMapper {

    public Projeto toEntity(ProjetoRequestDTO dto, Membro gerente) {

        return Projeto.builder()
                .nome(dto.getNome())
                .dataInicio(dto.getDataInicio())
                .previsaoTermino(dto.getPrevisaoTermino())
                .orcamentoTotal(dto.getOrcamentoTotal())
                .descricao(dto.getDescricao())
                .status(dto.getStatus())
                .classificacaoRisco(dto.getClassificacaoRisco())
                .gerenteResponsavel(gerente)
                .build();
    }

    public ProjetoResponseDTO toDTO(Projeto projeto) {

        return ProjetoResponseDTO.builder()
                .id(projeto.getId())
                .nome(projeto.getNome())
                .dataInicio(projeto.getDataInicio())
                .previsaoTermino(projeto.getPrevisaoTermino())
                .dataRealTermino(projeto.getDataRealTermino())
                .orcamentoTotal(projeto.getOrcamentoTotal())
                .descricao(projeto.getDescricao())
                .status(projeto.getStatus())
                .classificacaoRisco(projeto.getClassificacaoRisco())
//                .gerenteId(
//                        projeto.getGerenteResponsavel() != null
//                                ? projeto.getGerenteResponsavel().getId()
//                                : null
//                )
                .build();
    }
}
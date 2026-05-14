package com.guilherme.projectportfolioapi.mapper;

import com.guilherme.projectportfolioapi.dto.response.ProjetoResponseDTO;
import com.guilherme.projectportfolioapi.entity.Projeto;
import com.guilherme.projectportfolioapi.enums.ClassificacaoRisco;
import org.springframework.stereotype.Component;

@Component
public class ProjetoMapper {

    public ProjetoResponseDTO toDTO(
            Projeto projeto,
            ClassificacaoRisco risco
    ) {

        return new ProjetoResponseDTO(
                projeto.getId(),
                projeto.getNome(),
                projeto.getDataInicio(),
                projeto.getPrevisaoTermino(),
                projeto.getDataRealTermino(),
                projeto.getOrcamentoTotal(),
                projeto.getDescricao(),
                projeto.getGerente() != null
                        ? projeto.getGerente().getNome()
                        : null,
                projeto.getStatus(),
                risco
        );
    }
}
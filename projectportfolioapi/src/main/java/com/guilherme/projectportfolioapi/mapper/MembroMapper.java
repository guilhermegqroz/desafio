package com.guilherme.projectportfolioapi.mapper;

import com.guilherme.projectportfolioapi.dto.request.MembroRequestDTO;
import com.guilherme.projectportfolioapi.dto.response.MembroResponseDTO;
import com.guilherme.projectportfolioapi.entity.Membro;
import org.springframework.stereotype.Component;

@Component
public class MembroMapper {

    public Membro toEntity(MembroRequestDTO dto) {
        return Membro.builder()
                .nome(dto.getNome())
                .atribuicao(dto.getAtribuicao())
                .build();
    }

    public MembroResponseDTO toDTO(Membro membro) {
        return MembroResponseDTO.builder()
                .id(membro.getId())
                .nome(membro.getNome())
                .atribuicao(membro.getAtribuicao())
                .build();
    }
}
package com.guilherme.projectportfolioapi.service;

import com.guilherme.projectportfolioapi.dto.request.ProjetoRequestDTO;
import com.guilherme.projectportfolioapi.dto.response.ProjetoResponseDTO;
import com.guilherme.projectportfolioapi.enums.StatusProjeto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjetoService {

    ProjetoResponseDTO criar(ProjetoRequestDTO dto);

    ProjetoResponseDTO buscarPorId(Long id);

    Page<ProjetoResponseDTO> listar(
            StatusProjeto status,
            Pageable pageable
    );

    ProjetoResponseDTO atualizar(
            Long id,
            ProjetoRequestDTO dto
    );

    void deletar(Long id);

    ProjetoResponseDTO alterarStatus(
            Long id,
            StatusProjeto status
    );
}